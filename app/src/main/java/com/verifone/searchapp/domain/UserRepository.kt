package com.verifone.searchapp.domain

interface UserRepository {
    suspend fun searchUsers(username: String): Result<List<User>>
}