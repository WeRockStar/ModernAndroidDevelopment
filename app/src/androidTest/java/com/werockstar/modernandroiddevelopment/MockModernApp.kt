package com.werockstar.modernandroiddevelopment

import com.werockstar.modernandroiddevelopment.di.component.AppComponent
import com.werockstar.modernandroiddevelopment.user.DaggerUserActivityTest_TestComponent

class MockModernApp : ModernApp() {

    override fun createComponent(): AppComponent {
        return DaggerUserActivityTest_TestComponent.builder()
                .build()
    }
}