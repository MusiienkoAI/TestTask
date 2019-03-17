@file:JvmName("ContextUtils")

package auto.testtask.utils.extensions

import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri

import android.view.inputmethod.InputMethodManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Build
import auto.utilities.extensions.isAndroidOreoOrHigher
import java.lang.Exception


/**
 * Creates intent for specified [T] component of Android.
 *
 * @param action - value passed to [Intent.setAction]
 * @param flags - value passed to [Intent.setFlags]
 */
inline fun <reified T : Any> Context.intentFor(
    action: String? = null,
    flags: Int = -1
): Intent = Intent(this, T::class.java).apply {
    this.action = action
    this.flags = flags
}

/**
 * Creates intent for specified [T] component of Android and
 * initialize it with [init] block.
 */
inline fun <reified T : Any> Context.intentFor(
    action: String? = null,
    flags: Int = -1,
    init: Intent.() -> Unit
): Intent = intentFor<T>(action, flags).apply(init)

/**
 * Start activity for specified [T] Activity
 *
 * @param action - value passed to [Intent.setAction]
 * @param flags - value passed to [Intent.setFlags]
 */
inline fun <reified T : Activity> Context.startActivity(
    action: String? = null,
    flags: Int = -1
) = startActivity(intentFor<T>(action, flags))

/**
 * Start activity for specified [T] Activity
 *
 * @param action - value passed to [Intent.setAction]
 * @param flags - value passed to [Intent.setFlags]
 * @param init - function with [Intent] value as its receiver
 */
inline fun <reified T : Activity> Context.startActivity(
    action: String? = null,
    flags: Int = -1,
    init: Intent.() -> Unit
) = startActivity(intentFor<T>(action, flags, init))

/**
 * Start service for specified [T] Service
 *
 * @param action - value passed to [Intent.setAction]
 */
inline fun <reified T : Service> Context.startService(
    action: String? = null
): ComponentName? {
    return if (isAndroidOreoOrHigher()) startForegroundService(intentFor<T>(action = action))
    else startService(intentFor<T>(action = action))
}

/**
 * Start service for specified [T] Service
 *
 * @param action - value passed to [Intent.setAction]
 * @param init - function with [Intent] value as its receiver
 */
inline fun <reified T : Service> Context.startService(
    action: String? = null,
    init: Intent.() -> Unit
): ComponentName? {
    return if (isAndroidOreoOrHigher()) startForegroundService(intentFor<T>(action = action, init = init))
    else startService(intentFor<T>(action = action, init = init))
}

fun Activity.finishWithoutAnimation() {
    finish()
    overridePendingTransition(0, 0)
}


fun Activity.hidekeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun Activity.showkeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(currentFocus, 0)
}


fun Context.getBitmapFromUri(uri: Uri): Bitmap? {

    fun getRotatedBitmap(bitmap: Bitmap): Bitmap {

        fun rotateImage(source: Bitmap, angle: Float): Bitmap {
            val matrix = Matrix()
            matrix.postRotate(angle)
            return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
        }


        val ei = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ExifInterface(contentResolver.openInputStream(uri))
        } else {
            ExifInterface(uri.path)
        }
        val orientation = ei.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED
        )



        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                rotateImage(bitmap, 90f)
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> {
                rotateImage(bitmap, 180f)
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> {
                rotateImage(bitmap, 270f)
            }
            else -> {
                bitmap
            }

        }
    }

    contentResolver.notifyChange(uri, null)
    lateinit var bitmap: Bitmap
    try {
        bitmap = android.provider.MediaStore.Images.Media.getBitmap(contentResolver, uri)
    } catch (e: Exception) {

    }
    return getRotatedBitmap(bitmap)
}



