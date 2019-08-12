package id.poncoe.latihanmasterandroidponcoe.Java.belajarintent;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import id.poncoe.latihanmasterandroidponcoe.R;

public class IntentExplicitJava extends AppCompatActivity {
    public static final String EXTRA_AGE = "extra_age";
    public static final String EXTRA_NAME = "extra_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pindah_dengan_data);
        setTitle("Intent dengan Data");

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvDataReceived = findViewById(R.id.tv_data_received);

        /** Di sini kita akan mengirimkan data bertipe string ke IntentExplicitJava.
         * Di dalam MoveWithdataActivity kita akan mengambil nilai data berdasarkan key yang dikirimkan dengan menggunakan metode getIntent().getStringExtra(key).
         * Implementasinya sebagai berikut: */

        String name = getIntent().getStringExtra(EXTRA_NAME);

        /** Nilai dari variabel age yang bertipe data integer berasal dari getIntent().getIntExtra(Key, nilai default).
         * Key yang digunakan untuk mengirimkan dan mengambil data adalah EXTRA_AGE (yang bernilai “extra_age”).
         * Nilai default di sini merupakan nilai yang akan digunakan jika ternyata datanya kosong.
         * Data kosong atau nilainya null bisa terjadi ketika datanya memang tidak ada, atau key-nya tidak sama.   */

        int age = getIntent().getIntExtra(EXTRA_AGE, 0);
        String text = "Nama : " + name + ",\nUmur : " + age;
        tvDataReceived.setText(text);
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