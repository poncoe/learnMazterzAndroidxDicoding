package id.poncoe.latihanmasterandroidponcoe.kotlin.storage.mypreloaddata.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import android.provider.BaseColumns._ID
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.mypreloaddata.database.DatabaseContract.MahasiswaColumns.Companion.NAMA
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.mypreloaddata.database.DatabaseContract.MahasiswaColumns.Companion.NIM
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.mypreloaddata.database.DatabaseContract.TABLE_NAME

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "dbmahasiswa"

        private const val DATABASE_VERSION = 1

        private val CREATE_TABLE_MAHASISWA = "create table $TABLE_NAME ($_ID integer primary key autoincrement, $NAMA text not null, $NIM text not null);"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_MAHASISWA)
    }

    /*
    Method onUpgrade akan di panggil ketika terjadi perbedaan versi
    Gunakan method onUpgrade untuk melakukan proses migrasi data
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /*
        Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
         */
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}
