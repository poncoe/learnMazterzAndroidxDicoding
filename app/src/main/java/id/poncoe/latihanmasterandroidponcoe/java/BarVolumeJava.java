package id.poncoe.latihanmasterandroidponcoe.java;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.poncoe.latihanmasterandroidponcoe.R;

/** Menandakan bahwa kelas Java / Kotlin di atas merupakan sebuah activity
 * karena inherit ke superclass bernama AppCompatActivity. */

/** onClickListener Ini adalah listener yang kita implementasikan untuk memantau kejadian klik pada komponen tombol (button). */

public class BarVolumeJava extends AppCompatActivity implements View.OnClickListener {

    /** Kode di atas mendeklarasikan semua komponen view yang akan dimanipulasi.
     * Kita deklarasikan secara global agar bisa dikenal di keseluruhan bagian kelas. */

    private EditText edtWidth, edtHeight, edtLength;
    private Button btnCalculate;
    private TextView tvResult;
    private static final String STATE_RESULT = "state_result";

    /** Metode onCreate() merupakan metode utama pada activity. Di sinilah kita dapat mengatur layout xml.
     * Semua proses inisialisasi komponen yang digunakan akan dijalankan di sini.
     * Pada metode ini kita casting semua komponen view yang kita telah deklarasikan sebelumnya, agar dapat kita manipulasi. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Maksud baris di atas adalah kelas BarVolume akan menampilkan tampilan yang berasal dari layout volumebar.xml. */

        setContentView(R.layout.volumebar);
        setTitle("Bar Volume");

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /** Maksud dari baris diatas adalah obyek edittext edtWidth disesuaikan (cast)
         * dengan komponen edittext ber-ID edt_width di layout xml melalui metode findViewById(). */

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        /** Kita memasang event click listener untuk obyek btnCalculate sehingga sebuah aksi dapat dijalankan ketika obyek tersebut diklik.
         * Keyword this merujuk pada obyek Activity saat ini yang telah mengimplementasikan listener OnClickListener sebelumnya.  */

        btnCalculate.setOnClickListener(this);

        /** Perhatikan metode onSaveInstanceState. Di dalam metode tersebut, hasil perhitungan yang ditampilkan pada tvResult dimasukkan pada bundle kemudian disimpan isinya.
         * Setelah onSaveInstanceState berhasil dijalankan, maka activity akan melakukan proses onDestroy dan menjalankan kembali onCreate secara otomatis. */

        if (savedInstanceState != null){
            String hasil = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(hasil);
        }
    }

    /** Pada onCreate inilah kita menggunakan nilai bundle yang telah kita simpan sebelumnya pada onSaveInstanceState.
     * Nilai tersebut kita isikan kembali pada tvResult. */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_RESULT, tvResult.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edtLength.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                edtWidth.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edtHeight.setError("Field ini tidak boleh kosong");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null) {
                isInvalidDouble = true;
                edtLength.setError("Field ini harus berupa nomer yang valid");
            }

            if (width == null) {
                isInvalidDouble = true;
                edtWidth.setError("Field ini harus berupa nomer yang valid");
            }

            if (height == null) {
                isInvalidDouble = true;
                edtHeight.setError("Field ini harus berupa nomer yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length * width * height;
                tvResult.setText(String.valueOf(volume));
            }
        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
