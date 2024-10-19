package com.flasska.finapp.di

import android.content.Context
import com.flasska.findata.di.UseCasesModule
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.usecase.GetAllUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [UseCasesModule::class])
internal interface AppComponent {
    fun getAllUseCase(): GetAllUseCase<Expense>

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}