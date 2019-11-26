package id.poncoe.latihanmasterandroidponcoe.java.backend.mybroadcastreceiver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.poncoe.latihanmasterandroidponcoe.R;

public class SmsReceiverActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvSmsFrom;
    private TextView tvSmsMessage;
    private Button btnClose;

    public static final String EXTRA_SMS_NO = "extra_sms_no";
    public static final String EXTRA_SMS_MESSAGE = "extra_sms_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);

        setTitle(getString(R.string.incoming_message));

        tvSmsFrom = findViewById(R.id.tv_from);
        tvSmsMessage = findViewById(R.id.tv_message);
        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);

        String senderNo = getIntent().getStringExtra(EXTRA_SMS_NO);
        String senderMessage = getIntent().getStringExtra(EXTRA_SMS_MESSAGE);

        tvSmsFrom.setText(String.format("from : %s", senderNo));
        tvSmsMessage.setText(senderMessage);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_close) {
            finish();
        }
    }
}
