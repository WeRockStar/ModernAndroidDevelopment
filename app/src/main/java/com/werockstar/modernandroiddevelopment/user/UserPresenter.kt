package com.werockstar.modernandroiddevelopment.user

import com.werockstar.modernandroiddevelopment.api.SimpleApi
import com.werockstar.modernandroiddevelopment.model.UserResponse
import com.werockstar.modernandroiddevelopment.rx.RxThread
import javax.inject.Inject

class UserPresenter @Inject constructor(val api: SimpleApi, val threadService: RxThread) {

    private lateinit var view: UserPresenter.View

    interface View {
        fun onShowProgressBar()
        fun onDismissShowProgressBar()
        fun onUserResult(user: UserResponse)
        fun onUserNotFound()
    }

    fun attachView(view: View) {
        this.view = view
    }

    fun getUserById(userId: Int) {
        view.onShowProgressBar()
        api.getUserById(userId)
                .flatMap({ user ->
                    api.getUserImageUrlById(userId)
                            .map { UserResponse(userId, user.name, it.url) }
                })
                .compose(threadService.applyAsync())
                .doOnTerminate({ view.onDismissShowProgressBar() })
                .subscribe({
                    view.onUserResult(it)
                }, {
                })
    }

}