package id.poncoe.latihanmasterandroidponcoe.java.belajarintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import id.poncoe.latihanmasterandroidponcoe.R;

public class BelajarPindahActivityJava extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.belajarintent_pindahactivity);
        setTitle("Latihan Intent");

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        Button btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnMoveWithDataActivity.setOnClickListener(this);

        Button btnDialPhone = findViewById(R.id.btn_dial_number);
        btnDialPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_move_activity:

                /** Kita membuat suatu obyek Intent dengan cara seperti di atas dengan memberikan kelas Activity asal (BelajarPindahActivityJava.this)
                 * dan kelas Activity tujuan (PindahActivityJava.class) pada konstruktor kelas Intent. */

                Intent moveIntent = new Intent(BelajarPindahActivityJava.this, PindahActivityJava.class);

                /** startActivity(moveIntent) metode ini akan menjalankan activity baru tanpa membawa data.
                 * Obyek intent yang diinputkan adalah obyek moveIntent yang akan ketika kode ini dijalankan maka akan membuka PindahActivity. */

                startActivity(moveIntent);
                break;

                /** Perbedaan mendasar antara memindahkan Activity dengan membawa data atau tidak, adalah dengan menempatkan data ke obyek Intent pada baris ini. */

            case R.id.btn_move_activity_data:
                Intent moveWithDataIntent = new Intent(BelajarPindahActivityJava.this, IntentExplicitJava.class);

                /** Kita memanfaatkan metode putExtra() untuk mengirimkan data bersamaan dengan obyek Intent.
                 * Sedangkan metode putExtra() itu sendiri merupakan metode yang menampung pasangan key-value dan memiliki beberapa pilihan tipe input */

                /** MoveWithDataActivity.EXTRA_NAME dimana EXTRA_NAME adalah variabel static bertipe data string dan bernilai “extra_name” pada IntentExplicit.java.
                 * Penentuan nilai untuk key parameter untuk intent adalah bebas, di sini kami merekomendasikan format terbaik yang biasa diimplementasikan. */

                moveWithDataIntent.putExtra(IntentExplicitJava.EXTRA_NAME, "Kucing Item Putih");
                moveWithDataIntent.putExtra(IntentExplicitJava.EXTRA_AGE, 6);
                startActivity(moveWithDataIntent);
                break;

                /**Dibawah Ini kita mengimplementasikan penggunaan intent secara implicit untuk melakukan proses dial sebuah nomor telepon.
                 * Pada bagian new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                 * kita menggunakan inputan new Intent(ACTION, Uri); pada konstruktor sewaktu menciptakan obyek Intent di mana:
                 *
                 Action           : Intent.ACTION_DIAL
                 Uri                  : Uri.parse("tel:"+phoneNumber)

                 Variabel ACTION_DIAL menentukan intent filter dari aplikasi-aplikasi yang bisa menangani action tersebut.
                 Di sini aplikasi yang memiliki kemampuan untuk komunikasi akan muncul pada opsi pilihan, sebagaimana ditampilkan ke pengguna.
                 Selain ACTION_DIAL, di Android sudah tersedia berbagai action yang tinggal didefinisikan sewaktu menciptakan obyek Intent untuk mengakomodir berbagai tujuan.*/

            case R.id.btn_dial_number:
                String phoneNumber = "112";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
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
