package com.werockstar.modernandroiddevelopment.user

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.werockstar.modernandroiddevelopment.ModernApp
import com.werockstar.modernandroiddevelopment.R

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as ModernApp).getComponent().inject(this)
    }
}
