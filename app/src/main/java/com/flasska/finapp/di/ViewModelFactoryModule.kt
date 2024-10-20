package com.flasska.finapp.di

import com.flasska.finapp.ui.elements.dialogadd.expense.ExpenseAddingViewModel
import com.flasska.finapp.ui.elements.dialogadd.type.ExpenseTypeAddingViewModel
import com.flasska.finapp.ui.screens.dayscreen.DayScreenViewModel
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.usecase.AddUseCase
import com.flasska.findomain.usecase.GetAllUseCase
import com.flasska.findomain.usecase.GetDayStatisticUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun provideDayViewModelFactoryWrapper(
        getDayStatisticUseCase: GetDayStatisticUseCase
    ) = DayScreenViewModel.FactoryWrapper(getDayStatisticUseCase)

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
}