package com.flasska.findata.di

import android.content.Context
import androidx.room.Room
import com.flasska.findata.db.AppDatabase
import com.flasska.findata.db.ExpenseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DbModule {
    @Provides
    @Singleton
    fun provideDao(
        context: Context
    ): ExpenseDao = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = "fin_db"
    )
        .build()
        .getDao()
}