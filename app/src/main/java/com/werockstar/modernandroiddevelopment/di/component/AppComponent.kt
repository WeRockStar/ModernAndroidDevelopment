package com.werockstar.modernandroiddevelopment.di.component

import com.werockstar.modernandroiddevelopment.di.module.HttpModule
import com.werockstar.modernandroiddevelopment.di.module.RxThreadModule
import com.werockstar.modernandroiddevelopment.user.UserActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(HttpModule::class, RxThreadModule::class))
interface AppComponent {

    fun inject(activity: UserActivity)
}