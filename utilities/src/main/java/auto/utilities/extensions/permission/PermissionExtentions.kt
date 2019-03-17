package blocksdna.utilities.extensions.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.fragment.app.Fragment
import androidx.core.content.PermissionChecker

fun isLollipopOrBellow(): Boolean {
    return Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP
}

fun Context.isPermissionGranted(permission: AppPermission): Boolean {
    return PermissionChecker.checkSelfPermission(this, permission.permissionName) == PackageManager.PERMISSION_GRANTED
}

fun Fragment.isRationaleNeeded(permission: AppPermission): Boolean {
    return shouldShowRequestPermissionRationale(activity as Activity, permission.permissionName)
}

fun Activity.isRationaleNeeded(permission: AppPermission): Boolean {
    return shouldShowRequestPermissionRationale(this, permission.permissionName)
}

fun Fragment.requestPermission(permission: AppPermission) {
    requestPermissions(arrayOf(permission.permissionName), permission.requestCode)
}

fun Activity.requestPermission(permission: AppPermission) {
    requestPermissions(this, arrayOf(permission.permissionName), permission.requestCode)
}

/**
 * In Fragment where you want to perform operation that needs system permission use this function
 * NOTE: This function doesn't request permission if such has not been granted
 * [permission] - permission for which we are creating request (e.g AppPermission.CAMERA_PERMISSION)
 * [onGranted] - lambda block that will be executed if permission is granted.
 * [onDenied] - lambda block that will be executed if permission is not granted.
 * [onRationaleNeeded]- lambda block that will be executed if additional explanation for permission is needed.
 **/
inline fun Fragment.handlePermission(
    permission: AppPermission,
    onGranted: (AppPermission) -> Unit,
    onDenied: (AppPermission) -> Unit,
    onRationaleNeeded: (AppPermission) -> Unit
) {
    when {
        isLollipopOrBellow() || context?.isPermissionGranted(permission) == true -> onGranted(permission)
        isRationaleNeeded(permission) -> onRationaleNeeded(permission)
        else -> onDenied(permission)
    }
}

/**
 * In Fragment where you want to perform operation that needs system permission use this function
 * NOTE: This function doesn't request permission if such has not been granted
 * [permission] - permission for which we are creating request (e.g AppPermission.CAMERA_PERMISSION)
 * [onGranted] - lambda block that will be executed if permission is granted.
 * [onDenied] - lambda block that will be executed if permission is not granted.
 * [onRationaleNeeded]- lambda block that will be executed if additional explanation for permission is needed.
 **/
inline fun Activity.handlePermission(
    permission: AppPermission,
    onGranted: (AppPermission) -> Unit,
    onDenied: (AppPermission) -> Unit,
    onRationaleNeeded: (AppPermission) -> Unit
) {
    when {
        isLollipopOrBellow() || isPermissionGranted(permission) -> onGranted(permission)
        isRationaleNeeded(permission) -> onRationaleNeeded(permission)
        else -> onDenied(permission)
    }
}

/**
 * In Fragment where you want to perform operation that needs system permission use this function.
 * If the permission was not granted, it requests it
 * [permission] - permission for which we are creating request (e.g AppPermission.CAMERA_PERMISSION)
 * [onGranted] - lambda block that will be executed if permission is granted.
 * [onRationaleNeeded]- lambda block that will be executed if additional explanation for permission is needed.
 * @see Fragment.onRequestPermissionsResult
 **/
inline fun Fragment.handlePermission(
    permission: AppPermission,
    onGranted: (AppPermission) -> Unit,
    onRationaleNeeded: (AppPermission) -> Unit
) {
    when {
        isLollipopOrBellow() || context?.isPermissionGranted(permission) == true -> onGranted(permission)
        isRationaleNeeded(permission) -> onRationaleNeeded(permission)
        else -> requestPermission(permission)
    }
}

/**
 * In Fragment where you want to perform operation that needs system permission use this function.
 * If the permission was not granted, it requests it
 * [permission] - permission for which we are creating request (e.g AppPermission.CAMERA_PERMISSION)
 * [onGranted] - lambda block that will be executed if permission is granted.
 * [onRationaleNeeded]- lambda block that will be executed if additional explanation for permission is needed.
 * @see Activity.onRequestPermissionsResult
 **/
inline fun Activity.handlePermission(
    permission: AppPermission,
    onGranted: (AppPermission) -> Unit,
    onRationaleNeeded: (AppPermission) -> Unit
) {
    when {
        isLollipopOrBellow() || isPermissionGranted(permission) -> onGranted(permission)
        isRationaleNeeded(permission) -> onRationaleNeeded(permission)
        else -> requestPermission(permission)
    }
}

/**
 * In Activity or Fragment where you need permission you should override
 * [Activity.onRequestPermissionsResult] or [Fragment.onRequestPermissionsResult] function and delegate
 * its parameters to [onRequestPermissionsResultReceived] extension function
 **/
inline fun onRequestPermissionsResultReceived(
    requestCode: Int, permissions: Array<out String>,
    grantResults: IntArray,
    onPermissionGranted: (AppPermission) -> Unit,
    onPermissionDenied: (AppPermission) -> Unit
) {
    AppPermission.permissions.find { it.requestCode == requestCode }?.let {
        val permissionGrantResult = permissions
            .mapIndexedTo(mutableListOf()) { index, permission -> permission to grantResults[index] }
            .toMap()[it.permissionName]
        if (PackageManager.PERMISSION_GRANTED == permissionGrantResult) {
            onPermissionGranted(it)
        } else {
            onPermissionDenied(it)
        }
    }
}