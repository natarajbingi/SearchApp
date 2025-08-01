package com.verifone.searchapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("users")
    suspend fun searchUsers(@Query("username") username: String): Response<List<UserResponse>>

}