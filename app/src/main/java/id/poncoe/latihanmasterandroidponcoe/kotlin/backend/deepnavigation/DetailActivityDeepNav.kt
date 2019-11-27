package id.poncoe.latihanmasterandroidponcoe.kotlin.backend.deepnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.poncoe.latihanmasterandroidponcoe.R
import kotlinx.android.synthetic.main.activity_detail_deepnav.*

class DetailActivityDeepNav : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_MESSAGE = "extra_message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_deepnav)

        /*
        Ambil data dari intent yang dikirimkan oleh notifikasi
         */
        val title = intent.getStringExtra(EXTRA_TITLE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        tv_title.text = title
        tv_message.text = message
    }
}
