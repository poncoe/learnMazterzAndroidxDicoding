package id.poncoe.latihanmasterandroidponcoe.java.recyclerview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import id.poncoe.latihanmasterandroidponcoe.R;

public class MainRecyclerView extends AppCompatActivity {
    private RecyclerView rvHeroes;
    private ArrayList<Kucing> list = new ArrayList<>();
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_item);
        setActionBarTitle(title);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvHeroes = findViewById(R.id.rv_hero);

        /** Baris di bawah menjelaskan bahwa bila fixed size bernilai true, maka RecyclerView dapat melakukan optimasi ukuran lebar dan tinggi secara otomatis.
         * Nilai lebar dan tinggi RecyclerView menjadi konstan. Terlebih jika kita memiliki koleksi data yang dinamis untuk proses penambahan, perpindahan,
         * dan pengurangan item dari koleksi data. */

        rvHeroes.setHasFixedSize(true);

        list.addAll(DataKucing.getListData());
        showRecyclerList();
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showSelectedHero(Kucing kucing) {
        Toast.makeText(this, "Kamu memilih " + kucing.getName(), Toast.LENGTH_SHORT).show();
    }

    /** Pada sisi fleksibilitas setLayoutManager(), RecyclerView memiliki beragam bentuk yang disesuaikan dengan design yang diinginkan.
     * Kita hanya perlu menentukan nilai pada metode setLayoutManager() saja untuk menentukan bagaimana RecyclerView ditampilkan. */

    private void showRecyclerList(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListKucingAdapter listKucingAdapter = new ListKucingAdapter(list,this);
        rvHeroes.setAdapter(listKucingAdapter);

        listKucingAdapter.setOnItemClickCallback(new ListKucingAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Kucing data) {
                showSelectedHero(data);
            }
        });

    }

    private void showRecyclerGrid(){
        rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        GridKucingAdapter gridKucingAdapter = new GridKucingAdapter(list);
        rvHeroes.setAdapter(gridKucingAdapter);

        gridKucingAdapter.setOnItemClickCallback(new GridKucingAdapter.OnItemClickCallbackGrid() {
            @Override
            public void onItemClicked(Kucing data) {
                showSelectedHero(data);
            }
        });

    }

    private void showRecyclerCardView(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardviewKucingAdapter cardviewKucingadapter = new CardviewKucingAdapter(list,this);
        rvHeroes.setAdapter(cardviewKucingadapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
                break;

            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                title = "Mode CardView";
                showRecyclerCardView();
                break;
        }
        setActionBarTitle(title);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}