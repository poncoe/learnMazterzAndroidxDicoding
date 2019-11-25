package id.poncoe.latihanmasterandroidponcoe.kotlin.storage.contentprovider.consumerapp.db

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {

    // Authority yang digunakan
    const val AUTHORITY = "id.poncoe.latihanmasterandroidponcoe.kotlin.storage.contentprovider.consumerapp"
    const val SCHEME = "content"

    /*
    Penggunaan Base Columns akan memudahkan dalam penggunaan suatu table
    Untuk id yang autoincrement sudah default ada di dalam kelas BaseColumns dengan nama field _ID
     */
    class NoteColumns : BaseColumns {

        companion object {
            const val TABLE_NAME = "note"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"

            // Base content yang digunakan untuk akses content provider
            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                    .authority(AUTHORITY)
                    .appendPath(TABLE_NAME)
                    .build()
        }

    }
}
