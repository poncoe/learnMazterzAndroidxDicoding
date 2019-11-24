package id.poncoe.latihanmasterandroidponcoe.java.storage.sharedpreferences.mysettingpreference;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.poncoe.latihanmasterandroidponcoe.R;

public class MainSettingPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_preferences);
        getSupportFragmentManager().beginTransaction().add(R.id.setting_holder, new MyPreferenceFragment()).commit();
    }
}
