package com.flasska.finapp.di

import com.flasska.finapp.ui.elements.dialog.expense.ExpenseAddingViewModel
import com.flasska.finapp.ui.elements.dialog.statistic.StatisticViewModel
import com.flasska.finapp.ui.elements.dialog.type.ExpenseTypeAddingViewModel
import com.flasska.finapp.ui.screens.dayscreen.DayScreenViewModel
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.usecase.AddUseCase
import com.flasska.findomain.usecase.GetAllUseCase
import com.flasska.findomain.usecase.GetAverageUseCase
import com.flasska.findomain.usecase.GetDayExpensesUseCase
import com.flasska.findomain.usecase.GetMonthExpensesUseCase
import com.flasska.findomain.usecase.GetTypeToSumUseCase
import com.flasska.findomain.usecase.GetSumUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun provideDayViewModelFactoryWrapper(
        getDayExpensesUseCase: GetDayExpensesUseCase
    ) = DayScreenViewModel.FactoryWrapper(getDayExpensesUseCase)

    @Provides
    @Singleton
    fun provideExpenseAddViewModelFactoryWrapper(
        addUseCase: AddUseCase<Expense>,
        getTypesUseCase: GetAllUseCase<Expense.Type>
    ) = ExpenseAddingViewModel.FactoryWrapper(addUseCase, getTypesUseCase)

    @Provides
    @Singleton
    fun provideExpenseTypeAddViewModelFactoryWrapper(
        addUseCase: AddUseCase<Expense.Type>
    ) = ExpenseTypeAddingViewModel.FactoryWrapper(addUseCase)

    @Provides
    @Singleton
    fun provideStatisticViewModelFactoryWrapper(
        getMonthExpensesUseCase: GetMonthExpensesUseCase,
        getSumUseCase: GetSumUseCase,
        getAverageUseCase: GetAverageUseCase,
        getTypeToSumUseCase: GetTypeToSumUseCase
    ) = StatisticViewModel.FactoryWrapper(
        getMonthExpensesUseCase = getMonthExpensesUseCase,
        getSumUseCase = getSumUseCase,
        getAverageUseCase = getAverageUseCase,
        getTypeToSumUseCase = getTypeToSumUseCase
    )
}