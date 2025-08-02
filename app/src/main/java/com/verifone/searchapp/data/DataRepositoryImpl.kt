package com.verifone.searchapp.data

import com.verifone.searchapp.data.api.ApiService
import com.verifone.searchapp.data.api.ProductResponse
import com.verifone.searchapp.data.api.UserResponse
import com.verifone.searchapp.domain.Product
import com.verifone.searchapp.domain.User
import com.verifone.searchapp.domain.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : DataRepository {
    val TAG = "DataRepositoryImpl ->"
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

    override suspend fun searchProducts(query: String): Result<List<Product>> {
        return try {
            val response = apiService.searchProducts(query)
            if (response.isSuccessful) {
                val domainProducts = response.body()?.psdata?.products?.map { it.toDomainProduct() } ?: emptyList()
                Result.success(domainProducts)
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

fun ProductResponse.toDomainProduct(): Product {
    return Product(
        id = this.id_product,
        name = this.name,
        description = this.description,
        dateAdded = this.date_add,
        regularPrice = this.regular_price,
        imageUrl = this.cover?.url
    )
}