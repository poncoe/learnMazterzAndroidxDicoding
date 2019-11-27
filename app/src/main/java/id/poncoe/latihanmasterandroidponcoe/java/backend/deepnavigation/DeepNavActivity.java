package id.poncoe.latihanmasterandroidponcoe.java.backend.deepnavigation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;

import id.poncoe.latihanmasterandroidponcoe.R;

public class DeepNavActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deepnav);

        Button btnOpenDetail = findViewById(R.id.btn_open_detail);
        btnOpenDetail.setOnClickListener(this);

        showNotification(DeepNavActivity.this, getResources().getString(R.string.notification_title), getResources().getString(R.string.notification_message), 110);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_open_detail) {
            /*
            Intent yang akan dikirimkan ke halaman detail
             */
            Intent detailIntent = new Intent(DeepNavActivity.this, DetailActivityDeepNav.class);
            detailIntent.putExtra(DetailActivityDeepNav.EXTRA_TITLE, getString(R.string.detail_title));
            detailIntent.putExtra(DetailActivityDeepNav.EXTRA_MESSAGE, getString(R.string.detail_message));
            startActivity(detailIntent);
        }
    }

    /**
     * Tampilan notifikasi dan ditambahkan intent untuk redirect ke halaman detail
     *
     * @param context context activity
     * @param title   judul notifikasi
     * @param message pesan notifikasi
     * @param notifId id dari notifikasi
     */
    private void showNotification(Context context, String title, String message, int notifId) {
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "Navigation channel";

        Intent notifDetailIntent = new Intent(this, DetailActivityDeepNav.class);
        /*
        Intent yang akan dikirimkan ke halaman detail
        */
        notifDetailIntent.putExtra(DetailActivityDeepNav.EXTRA_TITLE, title);
        notifDetailIntent.putExtra(DetailActivityDeepNav.EXTRA_MESSAGE, message);

        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addParentStack(DetailActivityDeepNav.class)
                .addNextIntent(notifDetailIntent)
                .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_email_black_24dp)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.black))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            /* Create or update. */
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(CHANNEL_ID);

            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(notifId, notification);
        }

    }
}