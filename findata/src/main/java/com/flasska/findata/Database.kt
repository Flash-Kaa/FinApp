package com.flasska.findata

import androidx.room.Database

@Database(entities = [ExpenseDb::class], version = 1)
abstract class Database {
    abstract fun getDao(): ExpenseDao
}