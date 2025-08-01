package com.verifone.searchapp.data

import com.verifone.searchapp.data.api.ApiService
import com.verifone.searchapp.data.api.UserResponse
import com.verifone.searchapp.domain.User
import com.verifone.searchapp.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun searchUsers(username: String): Result<List<User>> {
        return try {
            val response = apiService.searchUsers(username)
            if (response.isSuccessful) {

                val domainUser = response.body()?.map { it.toDomainUser() } ?: emptyList()
                Result.success(domainUser)
            } else {
                Result.failure(Exception("API call failed with code: ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

fun UserResponse.toDomainUser(): User {
    return User(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email
    )
}