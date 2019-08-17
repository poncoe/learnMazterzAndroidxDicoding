package id.poncoe.latihanmasterandroidponcoe.Kotlin.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.poncoe.latihanmasterandroidponcoe.Kotlin.recyclerview.Kucing
import id.poncoe.latihanmasterandroidponcoe.R

class GridKucingAdapter(val listKucing: ArrayList<Kucing>) : RecyclerView.Adapter<GridKucingAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallbackGridKt

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallbackGridKt) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GridViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_grid_recyclerview, viewGroup, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {

        /** Glide digunakan untuk memuat sebuah gambar, baik yang sudah Anda siapkan di drawable maupun berada di server */
        Glide.with(holder.itemView.context)
                .load(listKucing[position].photo)
                .apply(RequestOptions().override(350, 550).centerCrop())
                .into(holder.imgPhoto)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listKucing[holder.adapterPosition]) }

    }

    interface OnItemClickCallbackGridKt {
        fun onItemClicked(data: Kucing)
    }

    override fun getItemCount(): Int {
        return listKucing.size
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }
}