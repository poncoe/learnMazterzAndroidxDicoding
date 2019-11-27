package id.poncoe.latihanmasterandroidponcoe.java.backend.deepnavigation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.poncoe.latihanmasterandroidponcoe.R;

public class DetailActivityDeepNav extends AppCompatActivity {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_MESSAGE = "extra_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_deepnav);

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvMessage = findViewById(R.id.tv_message);

        /*
        Ambil data dari intent yang dikirimkan oleh notifikasi
         */
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String message = getIntent().getStringExtra(EXTRA_MESSAGE);

        tvTitle.setText(title);
        tvMessage.setText(message);
    }
}
