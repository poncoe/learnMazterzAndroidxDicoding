package id.poncoe.latihanmasterandroidponcoe.java.backend.loader;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PremissionManager {
    public static void check(Activity activity, String permission, int requestCode) {
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }
    }
    public static boolean isGranted(Activity activity, String permission) {
        return (ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED);
    }
}