package com.verifone.searchapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val BASE_URL_SHOPPING = "https://rest.binshops.com/rest/"
    }

    @GET("users")
    suspend fun searchUsers(@Query("username") username: String): Response<List<UserResponse>>


    // This GET request will search for products based on the 's' query parameter.
    @GET("productSearch")
    suspend fun searchProducts(
        @Query("s") query: String,
        @Query("resultsPerPage") resultsPerPage: Int = 10
    ): Response<ProductSearchResponse>
}