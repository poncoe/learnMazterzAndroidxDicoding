package id.poncoe.latihanmasterandroidponcoe.Java.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import id.poncoe.latihanmasterandroidponcoe.R;

public class GridKucingAdapter extends RecyclerView.Adapter<GridKucingAdapter.GridViewHolder> {
    private ArrayList<Kucing> listKucing;

    public GridKucingAdapter(ArrayList<Kucing> list) {
        this.listKucing = list;
    }

    private OnItemClickCallbackGrid onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallbackGrid onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_recyclerview, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position) {

        /** Glide digunakan untuk memuat sebuah gambar, baik yang sudah Anda siapkan di drawable maupun berada di server */
        Glide.with(holder.itemView.getContext())
                .load(listKucing.get(position).getPhoto())
                .apply(new RequestOptions().override(350, 550).centerCropTransform())
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listKucing.get(holder.getAdapterPosition()));
            }
        });

    }

    public interface OnItemClickCallbackGrid {
        void onItemClicked(Kucing data);
    }

    @Override
    public int getItemCount() {
        return listKucing.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;

        GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}