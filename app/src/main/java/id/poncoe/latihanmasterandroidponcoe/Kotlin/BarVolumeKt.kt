package id.poncoe.latihanmasterandroidponcoe.Kotlin

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.poncoe.latihanmasterandroidponcoe.R
import android.view.MenuItem


/** Menandakan bahwa kelas Java / Kotlin di atas merupakan sebuah activity
 * karena inherit ke superclass bernama AppCompatActivity. */

/** onClickListener Ini adalah listener yang kita implementasikan untuk memantau kejadian klik pada komponen tombol (button). */

class BarVolumeKt : AppCompatActivity(), View.OnClickListener {

    /** Kode di atas mendeklarasikan semua komponen view yang akan dimanipulasi.
     * Kita deklarasikan secara global agar bisa dikenal di keseluruhan bagian kelas. */

    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    /** Metode onCreate() merupakan metode utama pada activity. Di sinilah kita dapat mengatur layout xml.
     * Semua proses inisialisasi komponen yang digunakan akan dijalankan di sini.
     * Pada metode ini kita casting semua komponen view yang kita telah deklarasikan sebelumnya, agar dapat kita manipulasi. */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Maksud baris di atas adalah kelas BarVolume akan menampilkan tampilan yang berasal dari layout volumebar.xml. */

        setContentView(R.layout.volumebar)
        title = "Bar Volume"

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        /** Maksud dari baris diatas adalah obyek edittext edtWidth disesuaikan (cast)
         * dengan komponen edittext ber-ID edt_width di layout xml melalui metode findViewById(). */

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        /** Kita memasang event click listener untuk obyek btnCalculate sehingga sebuah aksi dapat dijalankan ketika obyek tersebut diklik.
         * Keyword this merujuk pada obyek Activity saat ini yang telah mengimplementasikan listener OnClickListener sebelumnya.  */

        btnCalculate.setOnClickListener(this)

        /** Perhatikan metode onSaveInstanceState. Di dalam metode tersebut, hasil perhitungan yang ditampilkan pada tvResult dimasukkan pada bundle kemudian disimpan isinya.
         * Setelah onSaveInstanceState berhasil dijalankan, maka activity akan melakukan proses onDestroy dan menjalankan kembali onCreate secara otomatis. */

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    /** Pada onCreate inilah kita menggunakan nilai bundle yang telah kita simpan sebelumnya pada onSaveInstanceState.
     * Nilai tersebut kita isikan kembali pada tvResult. */

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_calculate) {
            val inputLength = edtLength.text.toString().trim { it <= ' ' }
            val inputWidth = edtWidth.text.toString().trim { it <= ' ' }
            val inputHeight = edtHeight.text.toString().trim { it <= ' ' }

            var isEmptyFields = false
            var isInvalidDouble = false

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }

            val length = toDouble(inputLength)
            val width = toDouble(inputWidth)
            val height = toDouble(inputHeight)

            if (length == null) {
                isInvalidDouble = true
                edtLength.error = "Field ini harus berupa nomer yang valid"
            }

            if (width == null) {
                isInvalidDouble = true
                edtWidth.error = "Field ini harus berupa nomer yang valid"
            }

            if (height == null) {
                isInvalidDouble = true
                edtHeight.error = "Field ini harus berupa nomer yang valid"
            }

            if (!isEmptyFields && !isInvalidDouble) {
                val volume = length as Double * width as Double * height as Double
                tvResult.text = volume.toString()
            }
        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId === android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }

}