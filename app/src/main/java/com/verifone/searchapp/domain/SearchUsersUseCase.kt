package com.verifone.searchapp.domain

import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend operator fun invoke(query: String): Result<List<User>> {
        return dataRepository.searchUsers(username = query)
    }
}