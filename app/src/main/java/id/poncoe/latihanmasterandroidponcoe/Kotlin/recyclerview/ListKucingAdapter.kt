package id.poncoe.latihanmasterandroidponcoe.Kotlin.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.poncoe.latihanmasterandroidponcoe.R

class ListKucingAdapter(val listKucing: ArrayList<Kucing>, private val context: Context) : RecyclerView.Adapter<ListKucingAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallbackKt

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallbackKt) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_recyclerview, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, from, photo) = listKucing[position]

        /** Glide digunakan untuk memuat sebuah gambar, baik yang sudah Anda siapkan di drawable maupun berada di server */

        Glide.with(holder.itemView.context)
                .load(photo)
                .apply(RequestOptions().override(55, 55).centerCrop())
                .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvFrom.text = from
        holder.itemView.setOnClickListener {
            openDetailActivity(photo,name,from)
            Toast.makeText(holder.itemView.context, "Kamu memilih " + listKucing[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
        }
    }

    interface OnItemClickCallbackKt {
        fun onItemClicked(data: Kucing)
    }

    override fun getItemCount(): Int {
        return listKucing.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvFrom = itemView.findViewById<TextView>(R.id.tv_item_from)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    private fun openDetailActivity(vararg details: String) {
        val i = Intent(context, DetailM::class.java)
        i.putExtra("IMAGES_KEY", details[0])
        i.putExtra("TITLE_KEY", details[1])
        i.putExtra("ISI_KEY", details[2])

        context.startActivity(i)

    }
}