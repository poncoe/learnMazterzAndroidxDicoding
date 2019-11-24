package id.poncoe.latihanmasterandroidponcoe.kotlin.storage.sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.sqlite.db.DatabaseContract.NoteColumns.Companion.DATE
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.sqlite.db.DatabaseContract.NoteColumns.Companion.DESCRIPTION
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.sqlite.db.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.sqlite.db.DatabaseContract.NoteColumns.Companion.TITLE
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.sqlite.entity.Note
import java.util.*

class NoteHelper(context: Context) {

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var dataBaseHelper: DatabaseHelper
        private lateinit var database: SQLiteDatabase

        private var INSTANCE: NoteHelper? = null
        fun getInstance(context: Context): NoteHelper {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = NoteHelper(context)
                    }
                }
            }
            return INSTANCE as NoteHelper
        }
    }

    init {
        dataBaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    /**
     * Ambil data dari semua note yang ada di dalam database
     *
     * @return cursor hasil queryAll
     */
    fun queryAll(): Cursor {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                "$_ID ASC"
        )
    }

    /**
     * Ambil data dari note berdasarakan parameter id
     *
     * @param id id note yang dicari
     * @return cursor hasil queryAll
     */
    fun queryById(id: String): Cursor {
        return database.query(
                DATABASE_TABLE,
                null,
                "$_ID = ?",
                arrayOf(id),
                null,
                null,
                null,
                null)
    }

    /**
     * Simpan data ke dalam database
     *
     * @param values nilai data yang akan di simpan
     * @return long id dari data yang baru saja di masukkan
     */
    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    /**
     * Update data dalam database
     *
     * @param id     data dengan id berapa yang akan di update
     * @param values nilai data baru
     * @return int jumlah data yang ter-update
     */
    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    /**
     * Delete data dalam database
     *
     * @param id data dengan id berapa yang akan di delete
     * @return int jumlah data yang ter-delete
     */
    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }

    /**
     * Gunakan method ini untuk ambil semua note yang ada
     * Otomatis di parsing ke dalam model Note
     *
     * @return hasil getGetAllNotes berbentuk array model note
     */
    fun getAllNotes(): ArrayList<Note> {
        val arrayList = ArrayList<Note>()
        val cursor = database.query(
            DATABASE_TABLE, null, null, null, null, null,
                "$_ID ASC", null)
        cursor.moveToFirst()
        var note: Note
        if (cursor.count > 0) {
            do {
                note = Note()
                note.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID))
                note.title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE))
                note.description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION))
                note.date = cursor.getString(cursor.getColumnIndexOrThrow(DATE))

                arrayList.add(note)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    /**
     * Gunakan method ini untuk insertNote
     *
     * @param note model note yang akan dimasukkan
     * @return id dari data yang baru saja dimasukkan
     */
    fun insertNote(note: Note): Long {
        val args = ContentValues()
        args.put(TITLE, note.title)
        args.put(DESCRIPTION, note.description)
        args.put(DATE, note.date)
        return database.insert(DATABASE_TABLE, null, args)
    }


    /**
     * Gunakan method ini untuk updateNote
     *
     * @param note model note yang akan diubah
     * @return int jumlah dari row yang ter-updateNote, jika tidak ada yang diupdate maka nilainya 0
     */
    fun updateNote(note: Note): Int {
        val args = ContentValues()
        args.put(TITLE, note.title)
        args.put(DESCRIPTION, note.description)
        args.put(DATE, note.date)
        return database.update(DATABASE_TABLE, args, _ID + "= '" + note.id + "'", null)
    }

    /**
     * Gunakan method ini untuk deleteNote
     *
     * @param id id yang akan di deleteNote
     * @return int jumlah row yang di deleteNote
     */
    fun deleteNote(id: Int): Int {
        return database.delete(TABLE_NAME, "$_ID = '$id'", null)
    }
}