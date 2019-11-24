package id.poncoe.latihanmasterandroidponcoe.kotlin.storage.sharedpreferences.mysettingpreference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.poncoe.latihanmasterandroidponcoe.R
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.sharedpreferences.mysettingpreference.MyPreferenceFragment

class MainSettingSharedPreferences : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_preferences)

        supportFragmentManager.beginTransaction().add(R.id.setting_holder, MyPreferenceFragment()).commit()

    }
}
