package id.poncoe.latihanmasterandroidponcoe.java.storage.contentprovider.consumerapp.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    // Authority yang digunakan
    public static final String AUTHORITY = "id.poncoe.latihanmasterandroidponcoe.java.storage.contentprovider.consumerapp";
    private static final String SCHEME = "content";

    /*
    Penggunaan Base Columns akan memudahkan dalam penggunaan suatu table
    Untuk id yang autoincrement sudah default ada di dalam kelas BaseColumns dengan nama field _ID
     */
    public static final class NoteColumns implements BaseColumns {
        // Note table name
        public static final String TABLE_NAME = "note";

        // Note title
        public static final String TITLE = "title";
        // Note description
        public static final String DESCRIPTION = "description";
        // Note date
        public static final String DATE = "date";

        // Base content yang digunakan untuk akses content provider
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

    }
}
