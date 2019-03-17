package blocksdna.utilities.extensions.permission

import android.Manifest

@Suppress("ClassName")
sealed class AppPermission(
    val permissionName: String,
    val requestCode: Int
) {
    companion object {
        val permissions: List<AppPermission> by lazy {
            listOf(
                READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE,
                ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION,
                CAMERA, CALL_PHONE,
                SEND_SMS, READ_SMS, RECEIVE_SMS, RECEIVE_MMS,
                READ_CALENDAR, WRITE_CALENDAR,
                READ_CONTACTS, WRITE_CONTACTS, GET_ACCOUNTS,
                RECORD_AUDIO, READ_CALL_LOG,
                READ_PHONE_STATE, ADD_VOICE_MAIL, WRITE_CALL_LOG,
                USE_SIP, PROCESS_OUTGOING_CALLS,
                RECEIVE_MMS, RECEIVE_WAP_PUSH
            )
        }

    }

    object CAMERA : AppPermission(Manifest.permission.CAMERA, 1)
    object READ_CALENDAR : AppPermission(Manifest.permission.READ_CALENDAR, 2)
    object WRITE_CALENDAR : AppPermission(Manifest.permission.WRITE_CALENDAR, 3)
    object READ_CONTACTS : AppPermission(Manifest.permission.READ_CONTACTS, 4)
    object WRITE_CONTACTS : AppPermission(Manifest.permission.WRITE_CONTACTS, 5)
    object GET_ACCOUNTS : AppPermission(Manifest.permission.GET_ACCOUNTS, 6)
    object ACCESS_FINE_LOCATION : AppPermission(Manifest.permission.ACCESS_FINE_LOCATION, 7)
    object ACCESS_COARSE_LOCATION : AppPermission(Manifest.permission.ACCESS_COARSE_LOCATION, 8)
    object RECORD_AUDIO : AppPermission(Manifest.permission.RECORD_AUDIO, 9)
    object READ_PHONE_STATE : AppPermission(Manifest.permission.READ_PHONE_STATE, 10)
    object CALL_PHONE : AppPermission(Manifest.permission.CALL_PHONE, 11)
    object READ_CALL_LOG : AppPermission(Manifest.permission.READ_CALL_LOG, 12)
    object WRITE_CALL_LOG : AppPermission(Manifest.permission.WRITE_CALL_LOG, 13)
    object ADD_VOICE_MAIL : AppPermission(Manifest.permission.ADD_VOICEMAIL, 14)
    object USE_SIP : AppPermission(Manifest.permission.USE_SIP, 15)
    object PROCESS_OUTGOING_CALLS : AppPermission(Manifest.permission.PROCESS_OUTGOING_CALLS, 16)
    object SEND_SMS : AppPermission(Manifest.permission.SEND_SMS, 18)
    object RECEIVE_SMS : AppPermission(Manifest.permission.RECEIVE_SMS, 19)
    object READ_SMS : AppPermission(Manifest.permission.READ_SMS, 20)
    object RECEIVE_WAP_PUSH : AppPermission(Manifest.permission.RECEIVE_WAP_PUSH, 21)
    object RECEIVE_MMS : AppPermission(Manifest.permission.RECEIVE_MMS, 22)
    object READ_EXTERNAL_STORAGE : AppPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 23)
    object WRITE_EXTERNAL_STORAGE : AppPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 24)
}