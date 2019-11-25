package id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.poncoe.latihanmasterandroidponcoe.R;
import id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata.adapter.MahasiswaAdapter;
import id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata.database.MahasiswaHelper;
import id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata.model.MahasiswaModel;

public class MahasiswaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MahasiswaAdapter mahasiswaAdapter = new MahasiswaAdapter();
        recyclerView.setAdapter(mahasiswaAdapter);

        MahasiswaHelper mahasiswaHelper = new MahasiswaHelper(this);
        mahasiswaHelper.open();
        // Ambil semua data mahasiswa di database
        ArrayList<MahasiswaModel> mahasiswaModels = mahasiswaHelper.getAllData();
        mahasiswaHelper.close();

        mahasiswaAdapter.setData(mahasiswaModels);
    }
}
