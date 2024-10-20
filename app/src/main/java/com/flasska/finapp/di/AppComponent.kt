package com.flasska.finapp.di

import android.content.Context
import com.flasska.finapp.ui.screens.dayscreen.DayScreenViewModel
import com.flasska.findata.di.UseCasesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [UseCasesModule::class, ViewModelFactoryModule::class])
internal interface AppComponent {
    fun provideDayScreenViewModelFactoryWrapper(): DayScreenViewModel.FactoryWrapper

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}