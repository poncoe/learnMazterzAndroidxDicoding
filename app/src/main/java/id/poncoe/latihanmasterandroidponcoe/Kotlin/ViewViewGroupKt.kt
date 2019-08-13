package id.poncoe.latihanmasterandroidponcoe.Kotlin

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import id.poncoe.latihanmasterandroidponcoe.R

class ViewViewGroupKt : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_viewgroup)

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val beli: Button = findViewById(R.id.btn_beli)
        beli.setOnClickListener(this)

        /** Baris dibawah ini akan mengganti nilai dari judul halaman pada ActionBar di dalam ViewViewGroup.
         * Kita menggunakan getSupportActionBar() karena kelas ViewViewGroup inherit kepada AppCompatActivity, yang merupakan kelas turunan Activity.
         * Kelas tersebut sudah menyediakan fasilitas komponen ActionBar dan mendukung semua versi OS Android. */

        if(supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Google Pixel"
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_beli -> {
                val toast = Toast.makeText(applicationContext, "Yay Kebeli", Toast.LENGTH_LONG)
                toast.show()
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