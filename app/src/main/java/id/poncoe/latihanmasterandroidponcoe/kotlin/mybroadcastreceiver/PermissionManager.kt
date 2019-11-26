package id.poncoe.latihanmasterandroidponcoe.kotlin.mybroadcastreceiver

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

/**
 * Kelas ini digunakan untuk grant permission secara run-time
 * grant permission secara run-time diharuskan untuk permission yang termasuk dalam kategori dangerous
 */
object PermissionManager {
    @kotlin.jvm.JvmStatic
    val granted: Boolean = false

    fun check(activity: Activity, permission: String, requestCode: Int) {
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        }
    }
}
