package com.flasska.findata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flasska.findata.entity.ExpenseTypeDb
import com.flasska.findata.entity.ExpenseValueDb

@Database(entities = [ExpenseValueDb::class, ExpenseTypeDb::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): ExpenseDao

    companion object {
        fun get(context: Context) = Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "fin_db1"
        ).build().getDao()
    }
}