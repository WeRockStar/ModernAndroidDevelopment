package com.werockstar.modernandroiddevelopment.user

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.werockstar.modernandroiddevelopment.ModernApp
import com.werockstar.modernandroiddevelopment.R
import com.werockstar.modernandroiddevelopment.model.UserResponse
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class UserActivity : AppCompatActivity(), UserPresenter.View {

    @Inject lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as ModernApp).component.inject(this)

        presenter.attachView(this)

        btnSearch.setOnClickListener({
            val userId = editTextUserId.text.toString().toInt()
            presenter.getUserById(userId)
        })
    }

    override fun onShowProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onDismissShowProgressBar() {
        progressBar.visibility = View.GONE
        ivUser.visibility = View.VISIBLE
    }

    override fun onUserResult(user: UserResponse) {
        tvName.text = user.name
        Glide.with(this).load(user.url).into(ivUser)
    }

    override fun onUserNotFound() {
        tvName.text = "User not found"
        Glide.with(this).load(R.mipmap.ic_launcher).into(ivUser)
    }
}
