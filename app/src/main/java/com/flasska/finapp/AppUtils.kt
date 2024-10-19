package com.flasska.finapp

import android.content.Context
import com.flasska.finapp.di.AppComponent

internal object AppUtils {
    val Context.appComponent: AppComponent
        get() = when (this) {
            is App -> this.appComponent
            else -> this.applicationContext.appComponent
        }
}