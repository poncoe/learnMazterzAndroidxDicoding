package id.poncoe.latihanmasterandroidponcoe.Java;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import id.poncoe.latihanmasterandroidponcoe.R;

public class ViewViewGroupJava extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_viewgroup);

        Button beli = findViewById(R.id.btn_beli);
        beli.setOnClickListener(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /** Baris dibawah ini akan mengganti nilai dari judul halaman pada ActionBar di dalam ViewViewGroup.
         * Kita menggunakan getSupportActionBar() karena kelas ViewViewGroup inherit kepada AppCompatActivity, yang merupakan kelas turunan Activity.
         * Kelas tersebut sudah menyediakan fasilitas komponen ActionBar dan mendukung semua versi OS Android. */

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Google Pixel");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_beli:
                Toast toast = Toast.makeText(getApplicationContext(), "Yey Kebeli", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}