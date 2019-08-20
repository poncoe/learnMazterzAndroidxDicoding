package id.poncoe.latihanmasterandroidponcoe.Java.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import id.poncoe.latihanmasterandroidponcoe.R;

public class DetailM extends AppCompatActivity {
    TextView nameTxt, usernameTxt;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameTxt = findViewById(R.id.nama_kucing);
        usernameTxt = findViewById(R.id.isi);
        img = findViewById(R.id.gambar);

        //DAPETIN INTENT
        Intent i = this.getIntent();

        //MENERIMA DATA
        String images = i.getExtras().getString("IMAGES_KEY");
        String name = i.getExtras().getString("TITLE_KEY");
        String username = i.getExtras().getString("ISI_KEY");

        //BIND DATA
        img.setImageURI(Uri.parse(images));
        nameTxt.setText(name);
        usernameTxt.setText(username);

        Glide.with(this)
                .load(images)
                //.resize(1200, 800)                     // optional
                .into(img);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
