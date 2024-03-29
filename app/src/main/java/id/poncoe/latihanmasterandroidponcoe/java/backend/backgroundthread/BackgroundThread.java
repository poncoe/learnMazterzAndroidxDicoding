package id.poncoe.latihanmasterandroidponcoe.java.backend.backgroundthread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.Objects;

import id.poncoe.latihanmasterandroidponcoe.R;

public class BackgroundThread extends AppCompatActivity implements MyAsyncCallback {

    private TextView tvStatus;
    private TextView tvDesc;

    private final static String INPUT_STRING = "Halo Ini Demo AsyncTask!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_thread);

        tvStatus = findViewById(R.id.tv_status);
        tvDesc = findViewById(R.id.tv_desc);

        DemoAsync demoAsync = new DemoAsync(this);

        // Execute asynctask dengan parameter string 'Halo Ini Demo AsyncTask'
        demoAsync.execute(INPUT_STRING);
    }


    @Override
    public void onPreExecute() {
        tvStatus.setText(R.string.status_pre);
        tvDesc.setText(INPUT_STRING);
    }

    @Override
    public void onPostExecute(String result) {
        tvStatus.setText(R.string.status_post);
        if (result != null) {
            tvDesc.setText(result);
        }
    }


    /**
     * 3 parameter generic <String, Void, String>
     * 1. Params, parameter input yang bisa dikirimkan
     * 2. Progress, digunakan untuk publish informasi sudah sampai mana proses background berjalan
     * 3. Result, object yang dikirimkan ke onPostExecute / hasil dari proses doInBackground
     *
     * dibawah ini adalah innner class
     */
    private static class DemoAsync extends AsyncTask<String, Void, String> {

        private static final String LOG_ASYNC = "DemoAsync";

        // Penggunaan weakreference disarankan untuk menghindari memory leaks
        private final WeakReference<MyAsyncCallback> myListener;

        private DemoAsync(MyAsyncCallback myListener) {
            this.myListener = new WeakReference<>(myListener);
        }

        /*
        onPreExecute digunakan untuk persiapan asynctask
        berjalan di Main Thread, bisa akses ke view karena masih di dalam Main Thread
         */

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(LOG_ASYNC, "status : onPreExecute");

            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null) {
                myListener.onPreExecute();
            }


        }

        /*
        doInBackground digunakan untuk menjalankan proses secara async
        berjalan di background thread, tidak bisa akses ke view karena sudah beda thread
         */
        @Override
        protected String doInBackground(String... params) {
            Log.d(LOG_ASYNC, "status : doInBackground");

            String output = null;

            try {

                /*
                params[0] adalah 'Halo Ini Demo AsyncTask'
                */

                String input = params[0];

                // Input stringnya ditambahkan dengan string ' Selamat Belajar!!"
                output = input + " Selamat Belajar!!";


                /*
                Sleep thread digunakan untuk simulasi bahwa ada proses yang sedang berjalan selama 2 detik
                2000 miliseconds = 2 detik
                */
                Thread.sleep(2000);

            } catch (Exception e) {
                Log.d(LOG_ASYNC, Objects.requireNonNull(e.getMessage()));
            }

            return output;
        }

        /*
        onPostExecute dijalankan ketika proses doInBackground telah selesai
        berjalan di Main Thread
         */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(LOG_ASYNC, "status : onPostExecute");

            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null) {
                myListener.onPostExecute(result);
            }
        }
    }
}
interface MyAsyncCallback {
    void onPreExecute();
    void onPostExecute(String text);
}