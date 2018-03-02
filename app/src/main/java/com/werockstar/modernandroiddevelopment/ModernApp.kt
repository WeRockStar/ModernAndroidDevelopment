package com.werockstar.modernandroiddevelopment

import android.app.Application
import com.werockstar.modernandroiddevelopment.di.component.AppComponent
import com.werockstar.modernandroiddevelopment.di.component.DaggerAppComponent

class ModernApp : Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder().build()
    }

    fun getComponent(): AppComponent {
        return component
    }
}