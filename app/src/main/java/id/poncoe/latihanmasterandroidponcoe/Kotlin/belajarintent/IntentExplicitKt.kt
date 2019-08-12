package id.poncoe.latihanmasterandroidponcoe.Kotlin.belajarintent

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.poncoe.latihanmasterandroidponcoe.R

class IntentExplicitKt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pindah_dengan_data)
        title = "Intent dengan Data"

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val tvDataReceived: TextView = findViewById(R.id.tv_data_received)

        /** Di sini kita akan mengirimkan data bertipe string ke IntentExplicitJava.
         * Di dalam MoveWithdataActivity kita akan mengambil nilai data berdasarkan key yang dikirimkan dengan menggunakan metode getIntent().getStringExtra(key).
         * Implementasinya sebagai berikut: */

        val name = intent.getStringExtra(EXTRA_NAME)

        /** Nilai dari variabel age yang bertipe data integer berasal dari getIntent().getIntExtra(Key, nilai default).
         * Key yang digunakan untuk mengirimkan dan mengambil data adalah EXTRA_AGE (yang bernilai “extra_age”).
         * Nilai default di sini merupakan nilai yang akan digunakan jika ternyata datanya kosong.
         * Data kosong atau nilainya null bisa terjadi ketika datanya memang tidak ada, atau key-nya tidak sama.   */

        val age = intent.getIntExtra(EXTRA_AGE, 0)
        val text = "Nama : $name, Umur : $age"
        tvDataReceived.text = text
    }

    companion object {
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId === android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }
}