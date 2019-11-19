package id.poncoe.latihanmasterandroidponcoe.java.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import id.poncoe.latihanmasterandroidponcoe.R;

public class CardviewKucingAdapter extends RecyclerView.Adapter<CardviewKucingAdapter.CardViewViewHolder> {
    private ArrayList<Kucing> listKucing;
    Context c;

    public CardviewKucingAdapter(ArrayList<Kucing> list, Context c) {
        this.listKucing = list;
        this.c = c;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_recyclerview, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {

        final Kucing kucing = listKucing.get(position);

        /** Glide digunakan untuk memuat sebuah gambar, baik yang sudah Anda siapkan di drawable maupun berada di server */
        Glide.with(holder.itemView.getContext())
                .load(kucing.getPhoto())
                .apply(new RequestOptions().override(350, 550).centerCropTransform())
                .into(holder.imgPhoto);

        final String images = kucing.getPhoto();
        final String title = kucing.getName();
        final String isi = kucing.getFrom();

        //BIND
        holder.imgPhoto.setImageURI(Uri.parse(images));
        holder.tvName.setText(title);
        holder.tvFrom.setText(isi);

        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Favorit " +
                        listKucing.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Bagikan " +
                        listKucing.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(images,title,isi);
                Toast.makeText(holder.itemView.getContext(), "Kamu memilih " + listKucing.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKucing.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvFrom;
        Button btnFavorite, btnShare;
        CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
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