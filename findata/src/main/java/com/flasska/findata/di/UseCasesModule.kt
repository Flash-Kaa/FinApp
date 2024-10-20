package com.flasska.findata.di

import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.Repository
import com.flasska.findomain.repository.StatisticRepository
import com.flasska.findomain.usecase.AddUseCase
import com.flasska.findomain.usecase.DeleteUseCase
import com.flasska.findomain.usecase.GetAllUseCase
import com.flasska.findomain.usecase.GetDayStatisticUseCase
import com.flasska.findomain.usecase.GetMonthStatisticUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoriesModule::class])
class UseCasesModule {
    @Provides
    fun provideGetAllUseCaseForTypes(
        @ExpenseTypeRepositoryQualifier repository: Repository<Expense.Type>
    ) = GetAllUseCase(repository)

    @Provides
    fun provideGetAllUseCaseForExpenses(
        @ExpenseRepositoryQualifier repository: Repository<Expense>
    ) = GetAllUseCase(repository)

    @Provides
    fun provideAddUseCaseForTypes(
        @ExpenseTypeRepositoryQualifier repository: Repository<Expense.Type>
    ) = AddUseCase(repository)

    @Provides
    fun provideAddUseCaseForExpenses(
        @ExpenseRepositoryQualifier repository: Repository<Expense>
    ) = AddUseCase(repository)

    @Provides
    fun provideDeleteUseCaseForTypes(
        @ExpenseTypeRepositoryQualifier repository: Repository<Expense.Type>
    ) = DeleteUseCase(repository)

    @Provides
    fun provideDeleteUseCaseForExpenses(
        @ExpenseRepositoryQualifier repository: Repository<Expense>
    ) = DeleteUseCase(repository)

    @Provides
    fun provideGetDayStatisticUseCase(
        repository: StatisticRepository
    ) = GetDayStatisticUseCase(repository)

    @Provides
    fun provideGetMonthStatisticRepository(
        repository: StatisticRepository
    ) = GetMonthStatisticUseCase(repository)
}