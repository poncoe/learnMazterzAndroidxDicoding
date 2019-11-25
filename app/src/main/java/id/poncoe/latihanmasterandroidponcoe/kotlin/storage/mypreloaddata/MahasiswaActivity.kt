package id.poncoe.latihanmasterandroidponcoe.kotlin.storage.mypreloaddata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.mypreloaddata.adapter.MahasiswaAdapter
import com.dicoding.picodiploma.mypreloaddata.database.MahasiswaHelper
import id.poncoe.latihanmasterandroidponcoe.R
import kotlinx.android.synthetic.main.activity_mahasiswa.*

class MahasiswaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa)

        recyclerview.layoutManager = LinearLayoutManager(this)
        val mahasiswaAdapter = MahasiswaAdapter()
        recyclerview.adapter = mahasiswaAdapter

        val mahasiswaHelper = MahasiswaHelper(this)
        mahasiswaHelper.open()
        // Ambil semua data mahasiswa di database
        val mahasiswaModels = mahasiswaHelper.getAllData()
        mahasiswaHelper.close()

        mahasiswaAdapter.setData(mahasiswaModels)
    }
}
