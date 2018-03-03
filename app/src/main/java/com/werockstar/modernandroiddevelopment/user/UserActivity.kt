package com.werockstar.modernandroiddevelopment.user

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.werockstar.modernandroiddevelopment.ModernApp
import com.werockstar.modernandroiddevelopment.R
import com.werockstar.modernandroiddevelopment.api.SimpleApi
import com.werockstar.modernandroiddevelopment.model.UserResponse
import com.werockstar.modernandroiddevelopment.rx.RxThread
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class UserActivity : AppCompatActivity() {

    @Inject lateinit var api: SimpleApi
    @Inject lateinit var threadService: RxThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as ModernApp).component.inject(this)

        btnSearch.setOnClickListener({
            val userId = editTextUserId.text.toString().toInt()
            progressBar.visibility = View.VISIBLE

            api.getUserById(userId)
                    .flatMap({ user ->
                        api.getUserImageUrlById(userId)
                                .map { UserResponse(userId, user.name, it.url) }
                    })
                    .compose(threadService.applyAsync())
                    .doOnTerminate({ progressBar.visibility = View.GONE })
                    .subscribe({
                        tvName.text = it.name
                        Glide.with(this).load(it.url).into(ivUser)
                    }, {
                        Log.e("User", it.message)
                        tvName.text = "User not found"
                        Glide.with(this).load(R.mipmap.ic_launcher).into(ivUser)
                    })
        })
    }
}
