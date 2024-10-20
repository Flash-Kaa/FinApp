package com.flasska.finapp.di

import com.flasska.finapp.ui.screens.dayscreen.DayScreenViewModel
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
}