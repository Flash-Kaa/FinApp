package com.flasska.findata.di

import com.flasska.findata.db.ExpenseDao
import com.flasska.findata.repository.ExpenseRepository
import com.flasska.findata.repository.ExpenseTypeRepository
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DbModule::class])
internal class RepositoriesModule {
    @Provides
    @Singleton
    @ExpenseRepositoryQualifier
    fun provideExpenseRepository(
        dao: ExpenseDao
    ): Repository<Expense> = ExpenseRepository(dao)

    @Provides
    @Singleton
    @ExpenseTypeRepositoryQualifier
    fun provideExpenseTypeRepository(
        dao: ExpenseDao
    ): Repository<Expense.Type> = ExpenseTypeRepository(dao)
}
