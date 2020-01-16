package com.caovr.phuc.hocquansu.model

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration

@Database(entities = arrayOf(Question::class), version = 2)
abstract class Appdatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    companion object {
        @JvmField
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }
}