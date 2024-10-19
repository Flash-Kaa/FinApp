package com.flasska.finapp

import android.app.Application
import com.flasska.finapp.di.AppComponent
import com.flasska.finapp.di.DaggerAppComponent

internal class App: Application() {
    lateinit var appComponent: AppComponent

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }


    override fun onCreate() {
        super.onCreate()
        initDagger()
    }
}