package com.flasska.findata.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flasska.findata.db.entity.ExpenseTypeDb
import com.flasska.findata.db.entity.ExpenseValueDb

@Database(entities = [ExpenseValueDb::class, ExpenseTypeDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): ExpenseDao
}