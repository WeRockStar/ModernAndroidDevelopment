package com.werockstar.modernandroiddevelopment

import android.app.Application
import com.werockstar.modernandroiddevelopment.di.component.AppComponent
import com.werockstar.modernandroiddevelopment.di.component.DaggerAppComponent
import com.werockstar.modernandroiddevelopment.di.module.HttpModule
import com.werockstar.modernandroiddevelopment.di.module.RxThreadModule

open class ModernApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = createComponent()
    }

    open fun createComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .httpModule(HttpModule())
                .rxThreadModule(RxThreadModule())
                .build()
    }
}