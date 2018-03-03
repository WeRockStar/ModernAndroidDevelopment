package com.werockstar.modernandroiddevelopment.user

import com.werockstar.modernandroiddevelopment.api.SimpleApi
import com.werockstar.modernandroiddevelopment.model.UrlResponse
import com.werockstar.modernandroiddevelopment.model.UserResponse
import com.werockstar.modernandroiddevelopment.rx.RxThread
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserPresenterTest {

    @Mock lateinit var api: SimpleApi
    @Mock lateinit var view: UserPresenter.View

    private lateinit var presenter: UserPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(api, RxThread(Schedulers.trampoline(), Schedulers.trampoline()))
        presenter.attachView(view)
    }

    @Test
    fun get_user_by_id_1_should_see_the_rock() {
        val userId = 1
        val userResponse = UserResponse(userId, "The Rock", null)
        val urlResponse = UrlResponse("https://vignette.wikia.nocookie.net/s4s/images/9/99/Rocke_22.jpg")

        Mockito.`when`(api.getUserById(userId)).thenReturn(Observable.just(userResponse))
        Mockito.`when`(api.getUserImageUrlById(userId)).thenReturn(Observable.just(urlResponse))

        presenter.getUserById(userId)

        Mockito.verify(view).onShowProgressBar()
        Mockito.verify(view).onUserResult(UserResponse(userId, userResponse.name, urlResponse.url))
        Mockito.verify(view).onDismissShowProgressBar()
    }
}
