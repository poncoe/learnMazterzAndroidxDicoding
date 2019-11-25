package id.poncoe.latihanmasterandroidponcoe.kotlin.storage.mypreloaddata.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.mypreloaddata.model.MahasiswaModel
import id.poncoe.latihanmasterandroidponcoe.R
import kotlinx.android.synthetic.main.item_mahasiswa_row.view.*

import java.util.ArrayList

class MahasiswaAdapter : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaHolder>() {
    private val listMahasiswa = ArrayList<MahasiswaModel>()

    fun setData(listMahasiswa: ArrayList<MahasiswaModel>) {

        if (listMahasiswa.size > 0) {
            this.listMahasiswa.clear()
        }

        this.listMahasiswa.addAll(listMahasiswa)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa_row, parent, false)
        return MahasiswaHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaHolder, position: Int) {
        holder.bind(listMahasiswa[position])
    }

    override fun getItemViewType(position: Int): Int = position

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = listMahasiswa.size

    inner class MahasiswaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mahasiswa: MahasiswaModel) {
            with(itemView){
                txt_nim.text = mahasiswa.nim
                txt_name.text = mahasiswa.name
            }
        }
    }
}