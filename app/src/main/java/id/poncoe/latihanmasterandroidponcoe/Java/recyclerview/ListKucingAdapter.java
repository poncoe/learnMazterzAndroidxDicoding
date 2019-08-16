package id.poncoe.latihanmasterandroidponcoe.Java.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import id.poncoe.latihanmasterandroidponcoe.R;

public class ListKucingAdapter extends RecyclerView.Adapter<ListKucingAdapter.ListViewHolder> {
    private ArrayList<Kucing> listKucing;
    Context c;

    public ListKucingAdapter(ArrayList<Kucing> list, Context c) {
        this.listKucing = list;
        this.c = c;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_recyclerview, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Kucing kucing = listKucing.get(position);

        /** Glide digunakan untuk memuat sebuah gambar, baik yang sudah Anda siapkan di drawable maupun berada di server */
        Glide.with(holder.itemView.getContext())
                .load(kucing.getPhoto())
                .apply(new RequestOptions().override(55, 55).centerCropTransform())
                .into(holder.imgPhoto);

        final String images = kucing.getPhoto();
        final String title = kucing.getName();
        final String isi = kucing.getFrom();

        //BIND
        holder.imgPhoto.setImageURI(Uri.parse(images));
        holder.tvName.setText(title);
        holder.tvFrom.setText(isi);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(images,title,isi);
                //onItemClickCallback.onItemClicked(listKucing.get(holder.getAdapterPosition()));
            }
        });

    }

    public interface OnItemClickCallback {
        void onItemClicked(Kucing data);
    }

    @Override
    public int getItemCount() {
        return listKucing.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvFrom;
        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
        }
    }

    ////open activity
    private void openDetailActivity(String... details) {
        Intent i = new Intent(c, DetailM.class);
        i.putExtra("IMAGES_KEY", details[0]);
        i.putExtra("TITLE_KEY", details[1]);
        i.putExtra("ISI_KEY", details[2]);
        c.startActivity(i);

    }
}