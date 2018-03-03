package com.werockstar.modernandroiddevelopment.api

import com.werockstar.modernandroiddevelopment.model.UrlResponse
import com.werockstar.modernandroiddevelopment.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface SimpleApi {

    @GET("user/{id}")
    fun getUserById(@Path("id") id: Int): Observable<UserResponse>

    @GET("user/{id}/url")
    fun getUserImageUrlById(@Path("id") id: Int): Observable<UrlResponse>
}