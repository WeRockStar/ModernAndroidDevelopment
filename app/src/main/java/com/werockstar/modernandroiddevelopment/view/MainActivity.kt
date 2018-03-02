package com.werockstar.modernandroiddevelopment.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.werockstar.modernandroiddevelopment.ModernApp
import com.werockstar.modernandroiddevelopment.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as ModernApp).getComponent().inject(this)
    }
}
