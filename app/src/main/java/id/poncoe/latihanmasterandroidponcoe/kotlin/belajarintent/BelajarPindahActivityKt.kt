package id.poncoe.latihanmasterandroidponcoe.kotlin.belajarintent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.poncoe.latihanmasterandroidponcoe.R
import kotlinx.android.synthetic.main.belajarintent_pindahactivity.*


class BelajarPindahActivityKt : AppCompatActivity(), View.OnClickListener {

    var tvResult: TextView? = null
    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.belajarintent_pindahactivity)
        title = "Latihan Intent"

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)
        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_move_activity -> {

                /** Kita membuat suatu obyek Intent dengan cara seperti di atas dengan memberikan kelas Activity asal (BelajarPindahActivityKt.this)
                 * dan kelas Activity tujuan (PindahActivityKt.class) pada konstruktor kelas Intent. */

                val moveIntent = Intent(this@BelajarPindahActivityKt, PindahActivityKt::class.java)

                /** startActivity(moveIntent) metode ini akan menjalankan activity baru tanpa membawa data.
                 * Obyek intent yang diinputkan adalah obyek moveIntent yang akan ketika kode ini dijalankan maka akan membuka PindahActivity. */

                startActivity(moveIntent)
            }

            /** Perbedaan mendasar antara memindahkan Activity dengan membawa data atau tidak, adalah dengan menempatkan data ke obyek Intent pada baris ini. */

            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this@BelajarPindahActivityKt, IntentExplicitKt::class.java)

                /** Kita memanfaatkan metode putExtra() untuk mengirimkan data bersamaan dengan obyek Intent.
                 * Sedangkan metode putExtra() itu sendiri merupakan metode yang menampung pasangan key-value dan memiliki beberapa pilihan tipe input */

                /** MoveWithDataActivity.EXTRA_NAME dimana EXTRA_NAME adalah variabel static bertipe data string dan bernilai “extra_name” pada IntentExplicit.java.
                 * Penentuan nilai untuk key parameter untuk intent adalah bebas, di sini kami merekomendasikan format terbaik yang biasa diimplementasikan. */

                moveWithDataIntent.putExtra(IntentExplicitKt.EXTRA_NAME, "Kucing Item Putih")
                moveWithDataIntent.putExtra(IntentExplicitKt.EXTRA_AGE, 6)
                startActivity(moveWithDataIntent)
            }

            /**Dibawah Ini kita mengimplementasikan penggunaan intent secara implicit untuk melakukan proses dial sebuah nomor telepon.
             * Pada bagian new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
             * kita menggunakan inputan new Intent(ACTION, Uri); pada konstruktor sewaktu menciptakan obyek Intent di mana:

            Action           : Intent.ACTION_DIAL
            Uri                  : Uri.parse("tel:"+phoneNumber)

            Variabel ACTION_DIAL menentukan intent filter dari aplikasi-aplikasi yang bisa menangani action tersebut.
            Di sini aplikasi yang memiliki kemampuan untuk komunikasi akan muncul pada opsi pilihan, sebagaimana ditampilkan ke pengguna.
            Selain ACTION_DIAL, di Android sudah tersedia berbagai action yang tinggal didefinisikan sewaktu menciptakan obyek Intent untuk mengakomodir berbagai tujuan.*/

            R.id.btn_dial_number -> {
                val phoneNumber = "112"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@BelajarPindahActivityKt, IntentWithResult::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == IntentWithResult.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(IntentWithResult.EXTRA_SELECTED_VALUE, 0)
                tv_result.text = "Hasil : $selectedValue"
            }
        }
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