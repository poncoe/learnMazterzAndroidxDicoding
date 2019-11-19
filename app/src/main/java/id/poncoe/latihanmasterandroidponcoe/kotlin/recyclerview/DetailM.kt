package id.poncoe.latihanmasterandroidponcoe.kotlin.recyclerview

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.poncoe.latihanmasterandroidponcoe.R

class DetailM : AppCompatActivity() {
    private lateinit var nameTxt: TextView
    private lateinit var usernameTxt: TextView
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        title = "Detail Penjelasan"

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        nameTxt = findViewById(R.id.nama_kucing)
        usernameTxt = findViewById(R.id.isi)
        img = findViewById(R.id.gambar)
        //GET INTENT
        val i = this.intent

        //RECEIVE DATA
        val images = i.extras!!.getString("IMAGES_KEY")
        val name = i.extras!!.getString("TITLE_KEY")
        val username = i.extras!!.getString("ISI_KEY")

        //BIND DATA
        img.setImageURI(Uri.parse(images))
        nameTxt.text = name
        usernameTxt.text = username

        Glide.with(this)
                .load(images)
                //.resize(1200, 800)                     // optional
                .into(img)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }

}