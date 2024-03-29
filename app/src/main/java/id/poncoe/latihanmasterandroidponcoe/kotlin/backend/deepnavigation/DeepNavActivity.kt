package id.poncoe.latihanmasterandroidponcoe.kotlin.backend.deepnavigation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import id.poncoe.latihanmasterandroidponcoe.R
import kotlinx.android.synthetic.main.activity_deepnav.*

class DeepNavActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deepnav)

        btn_open_detail.setOnClickListener(this)

        showNotification(this, getString(R.string.notification_title), getString(R.string.notification_message), 110)

    }


    override fun onClick(v: View) {
        if (v.id == R.id.btn_open_detail) {
            /*
            Intent yang akan dikirimkan ke halaman detail
             */
            val detailIntent = Intent(this, DetailActivityDeepNav::class.java)
            detailIntent.putExtra(DetailActivityDeepNav.EXTRA_TITLE, getString(R.string.detail_title))
            detailIntent.putExtra(DetailActivityDeepNav.EXTRA_MESSAGE, getString(R.string.detail_message))
            startActivity(detailIntent)
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
    private fun showNotification(context: Context, title: String, message: String, notifId: Int) {
        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "Navigation channel"

        val notifDetailIntent = Intent(this, DetailActivityDeepNav::class.java)
        /*
        Intent yang akan dikirimkan ke halaman detail
        */
        notifDetailIntent.putExtra(DetailActivityDeepNav.EXTRA_TITLE, title)
        notifDetailIntent.putExtra(DetailActivityDeepNav.EXTRA_MESSAGE, message)

        val pendingIntent = TaskStackBuilder.create(this)
                .addParentStack(DetailActivityDeepNav::class.java)
                .addNextIntent(notifDetailIntent)
                .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_email_black_24dp)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.black))
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setSound(alarmSound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            /* Create or update. */
            val channel = NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL_ID)

            notificationManagerCompat.createNotificationChannel(channel)
        }

        val notification = builder.build()

        notificationManagerCompat.notify(notifId, notification)
    }
}