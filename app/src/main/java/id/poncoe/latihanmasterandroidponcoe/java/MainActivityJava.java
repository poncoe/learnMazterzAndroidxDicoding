package id.poncoe.latihanmasterandroidponcoe.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import id.poncoe.latihanmasterandroidponcoe.java.belajarintent.BelajarPindahActivityJava;
import id.poncoe.latihanmasterandroidponcoe.java.recyclerview.MainRecyclerView;
import id.poncoe.latihanmasterandroidponcoe.kotlin.BarVolumeKt;
import id.poncoe.latihanmasterandroidponcoe.kotlin.ViewViewGroupKt;
import id.poncoe.latihanmasterandroidponcoe.kotlin.belajarintent.BelajarPindahActivityKt;
import id.poncoe.latihanmasterandroidponcoe.R;

public class MainActivityJava extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // onClickListener Java

        Button btn1 = findViewById(R.id.btn_jv_1);
        btn1.setOnClickListener(this);

        Button btn2 = findViewById(R.id.btn_jv_2);
        btn2.setOnClickListener(this);

        Button btn3 = findViewById(R.id.btn_jv_3);
        btn3.setOnClickListener(this);

        Button btn4 = findViewById(R.id.btn_jv_4);
        btn4.setOnClickListener(this);

        // onClickListener Kotlin

        Button btn5 = findViewById(R.id.btn_kt_1);
        btn5.setOnClickListener(this);

        Button btn6 = findViewById(R.id.btn_kt_2);
        btn6.setOnClickListener(this);

        Button btn7 = findViewById(R.id.btn_kt_3);
        btn7.setOnClickListener(this);

        Button btn8 = findViewById(R.id.btn_kt_4);
        btn8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // onClick untuk test Java

            case R.id.btn_jv_1:
                Intent mv1 = new Intent(MainActivityJava.this, BarVolumeJava.class);
                startActivity(mv1);
                break;
            case R.id.btn_jv_2:
                Intent mv2 = new Intent(MainActivityJava.this, BelajarPindahActivityJava.class);
                startActivity(mv2);
                break;
            case R.id.btn_jv_3:
                Intent mv3 = new Intent(MainActivityJava.this, ViewViewGroupJava.class);
                startActivity(mv3);
                break;
            case R.id.btn_jv_4:
                Intent mv4 = new Intent(MainActivityJava.this, MainRecyclerView.class);
                startActivity(mv4);
                break;

                // onClick untuk test Kotlin

            case R.id.btn_kt_1:
                Intent kt1 = new Intent(MainActivityJava.this, BarVolumeKt.class);
                startActivity(kt1);
                break;
            case R.id.btn_kt_2:
                Intent kt2 = new Intent(MainActivityJava.this, BelajarPindahActivityKt.class);
                startActivity(kt2);
                break;
            case R.id.btn_kt_3:
                Intent kt3 = new Intent(MainActivityJava.this, ViewViewGroupKt.class);
                startActivity(kt3);
                break;
            case R.id.btn_kt_4:
                Intent kt4 = new Intent(MainActivityJava.this, id.poncoe.latihanmasterandroidponcoe.kotlin.recyclerview.MainRecyclerView.class);
                startActivity(kt4);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
