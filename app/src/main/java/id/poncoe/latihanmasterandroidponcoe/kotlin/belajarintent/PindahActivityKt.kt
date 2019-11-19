package id.poncoe.latihanmasterandroidponcoe.kotlin.belajarintent

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import id.poncoe.latihanmasterandroidponcoe.R

class PindahActivityKt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pindah_activity)
        title = "Pindah Activity"

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
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