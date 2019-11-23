package id.poncoe.latihanmasterandroidponcoe.kotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.poncoe.latihanmasterandroidponcoe.R
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
}