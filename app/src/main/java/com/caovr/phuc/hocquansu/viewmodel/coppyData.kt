package com.caovr.phuc.hocquansu.viewmodel

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.io.FileOutputStream

class coppyData() {
    fun openDatabase(context: Context,DATABASE_NAME:String):SQLiteDatabase
    {
        val dbFile = context.getDatabasePath(DATABASE_NAME)
        if (!dbFile.exists())
        {
                val checkDB = context.openOrCreateDatabase(DATABASE_NAME,Context.MODE_PRIVATE,null)
                checkDB?.close()
                val inputstream = context.assets.open(DATABASE_NAME)
                val outputStream = FileOutputStream(dbFile)
                val buffer = ByteArray(1024)
                while (inputstream.read(buffer) > 0)
                {
                    outputStream.write(buffer)
                }
                outputStream.flush()
                outputStream.close()
                inputstream.close()

        }
        return SQLiteDatabase.openDatabase(dbFile.path,null,SQLiteDatabase.OPEN_READWRITE)
    }

    /*
    fun openDatabase(context: Context,DATABASE_NAME:String): SQLiteDatabase {
        val dbFile = context.getDatabasePath(DATABASE_NAME)


        if (!dbFile.exists()) {
            try {
                val checkDB = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null)

                checkDB?.close()
                copyDatabase(dbFile,context,DATABASE_NAME)
            } catch (e: IOException) {
                throw RuntimeException("Error creating source database", e)
            }

        }
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    @SuppressLint("WrongConstant")
    private fun copyDatabase(dbFile: File,context: Context,DATABASE_NAME: String) {
        val `is` = context.assets.open(DATABASE_NAME)
        val os = FileOutputStream(dbFile)

        val buffer = ByteArray(1024)
        while (`is`.read(buffer) > 0) {
            os.write(buffer)
            Log.d("#DB", "writing>>")
        }

        os.flush()
        os.close()
        `is`.close()
        Log.d("#DB", "completed..")
    }
    */
}