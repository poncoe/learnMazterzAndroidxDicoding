package id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.lang.ref.WeakReference;

import id.poncoe.latihanmasterandroidponcoe.R;
import id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata.services.DataManagerService;

import static id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata.services.DataManagerService.CANCEL_MESSAGE;
import static id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata.services.DataManagerService.FAILED_MESSAGE;
import static id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata.services.DataManagerService.PREPARATION_MESSAGE;
import static id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata.services.DataManagerService.SUCCESS_MESSAGE;
import static id.poncoe.latihanmasterandroidponcoe.java.storage.mypreloaddata.services.DataManagerService.UPDATE_MESSAGE;

public class MainPreloadData extends AppCompatActivity implements HandlerCallback {
    private ProgressBar progressBar;

    Messenger mBoundService;
    boolean mServiceBound;

    /*
    Service Connection adalah interface yang digunakan untuk menghubungkan antara boundservice dengan activity
    */
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBoundService = new Messenger(service);
            mServiceBound = true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloaddata);
        progressBar = findViewById(R.id.progress_bar);

        Intent mBoundServiceIntent = new Intent(MainPreloadData.this, DataManagerService.class);
        Messenger mActivityMessenger = new Messenger(new IncomingHandler(this));
        mBoundServiceIntent.putExtra(DataManagerService.ACTIVITY_HANDLER, mActivityMessenger);

        bindService(mBoundServiceIntent, mServiceConnection, BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(mServiceConnection);
    }

    @Override
    public void onPreparation() {
        Toast.makeText(this, "MEMULAI MEMUAT DATA", Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateProgress(long progress) {

        Log.e("PROGRESS", "updateProgress: " + progress);
        progressBar.setProgress((int) progress);
    }

    @Override
    public void loadSuccess() {
        Toast.makeText(this, "BERHASIL", Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainPreloadData.this, MahasiswaActivity.class));
        finish();
    }

    @Override
    public void loadFailed() {
        Toast.makeText(this, "GAGAL", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadCancel() {
        finish();
    }


    private static class IncomingHandler extends Handler {

        final WeakReference<HandlerCallback> weakCallback;

        IncomingHandler(HandlerCallback callback) {
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PREPARATION_MESSAGE:
                    weakCallback.get().onPreparation();
                    break;
                case UPDATE_MESSAGE:
                    Bundle bundle = msg.getData();
                    long progress = bundle.getLong("KEY_PROGRESS");
                    weakCallback.get().updateProgress(progress);
                    break;
                case SUCCESS_MESSAGE:
                    weakCallback.get().loadSuccess();
                    break;
                case FAILED_MESSAGE:
                    weakCallback.get().loadFailed();
                    break;
                case CANCEL_MESSAGE:
                    weakCallback.get().loadCancel();
                    break;
            }
        }
    }
}

interface HandlerCallback {
    void onPreparation();

    void updateProgress(long progress);

    void loadSuccess();

    void loadFailed();

    void loadCancel();
}