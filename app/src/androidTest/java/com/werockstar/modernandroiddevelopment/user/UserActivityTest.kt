package com.werockstar.modernandroiddevelopment.user

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.werockstar.modernandroiddevelopment.ModernApp
import com.werockstar.modernandroiddevelopment.R
import com.werockstar.modernandroiddevelopment.di.component.AppComponent
import com.werockstar.modernandroiddevelopment.di.module.HttpModule
import com.werockstar.modernandroiddevelopment.di.module.RxThreadModule
import dagger.Component
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Singleton

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserActivityTest {

    @get:Rule val activityRule = ActivityTestRule<UserActivity>(UserActivity::class.java, true, true)

    @Singleton
    @Component(modules = [HttpModule::class, RxThreadModule::class])
    interface TestComponent : AppComponent {
        fun inject(activity: UserActivityTest)
    }

    @Before
    fun setUp() {
        val instrument = InstrumentationRegistry.getInstrumentation()
        val app = instrument.targetContext.applicationContext as ModernApp
        val component = app.component as TestComponent
        component.inject(this)
    }

    @Test
    fun default_activity_textView_should_be_empty_and_imageView_should_invisible() {
        onView(withId(R.id.ivUser))
                .check(matches(not(isDisplayed())))
        onView(withId(R.id.tvName))
                .check(matches(withText("")))
    }

    @Test
    fun put_user_1_should_see_the_rock() {
        onView(withId(R.id.editTextUserId))
                .perform(typeText("1"))
                .perform(closeSoftKeyboard())

        onView(withId(R.id.btnSearch))
                .perform(ViewActions.click())

        onView(withId(R.id.tvName))
                .check(matches(withText("The Rock")))
    }
}