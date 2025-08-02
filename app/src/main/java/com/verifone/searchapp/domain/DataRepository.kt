package com.verifone.searchapp.domain

interface DataRepository {
    suspend fun searchUsers(username: String): Result<List<User>>
    suspend fun searchProducts(query: String): Result<List<Product>>
}