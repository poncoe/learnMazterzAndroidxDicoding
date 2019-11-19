package id.poncoe.latihanmasterandroidponcoe.kotlin.recyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.poncoe.latihanmasterandroidponcoe.R

class MainRecyclerView : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView
    private var list: ArrayList<Kucing> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_item)
        setActionBarTitle(title)

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        rvHeroes = findViewById(R.id.rv_hero)

        /** Baris di bawah menjelaskan bahwa bila fixed size bernilai true, maka RecyclerView dapat melakukan optimasi ukuran lebar dan tinggi secara otomatis.
         * Nilai lebar dan tinggi RecyclerView menjadi konstan. Terlebih jika kita memiliki koleksi data yang dinamis untuk proses penambahan, perpindahan,
         * dan pengurangan item dari koleksi data. */

        rvHeroes.setHasFixedSize(true)

        //list.addAll(DataKucing.listData())
        list.addAll(DataKucing.listData)
        showRecyclerList()
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }

    private fun showSelectedHero(kucing: Kucing) {
        Toast.makeText(this, "Kamu memilih " + kucing.name, Toast.LENGTH_SHORT).show()
    }

    /** Pada sisi fleksibilitas setLayoutManager(), RecyclerView memiliki beragam bentuk yang disesuaikan dengan design yang diinginkan.
     * Kita hanya perlu menentukan nilai pada metode setLayoutManager() saja untuk menentukan bagaimana RecyclerView ditampilkan. */

    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listKucingAdapter = ListKucingAdapter(list,this)
        rvHeroes.adapter = listKucingAdapter

        listKucingAdapter.setOnItemClickCallback(object : ListKucingAdapter.OnItemClickCallbackKt {
            override fun onItemClicked(data: Kucing) {
                showSelectedHero(data)
            }
        })

    }

    private fun showRecyclerGrid() {
        rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val gridKucingAdapter = GridKucingAdapter(list)
        rvHeroes.adapter = gridKucingAdapter

        gridKucingAdapter.setOnItemClickCallback(object : GridKucingAdapter.OnItemClickCallbackGridKt {
            override fun onItemClicked(data: Kucing) {
                showSelectedHero(data)
            }
        })

    }

    private fun showRecyclerCardView() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val cardviewKucingAdapter = CardviewKucingAdapter(list,this)
        rvHeroes.adapter = cardviewKucingAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_recyclerview, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        if (item.itemId === android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                showRecyclerList()
                title = "Mode List"
            }

            R.id.action_grid -> {
                showRecyclerGrid()
                title = "Mode Grid"
            }

            R.id.action_cardview -> {
                showRecyclerCardView()
                title = "Mode CardView"
            }
        }
        setActionBarTitle(title)
    }

    override fun onBackPressed() {
        finish()
    }

}