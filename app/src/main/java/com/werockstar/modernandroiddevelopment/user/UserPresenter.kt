package com.werockstar.modernandroiddevelopment.user

import com.werockstar.modernandroiddevelopment.model.UserResponse

class UserPresenter {

    interface View {
        fun onShowProgressBar()
        fun onDismissShowProgressBar()
        fun onUserResult(user: UserResponse)
        fun onUserNotFound()
    }

}