package com.verifone.searchapp.domain

import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(query: String): Result<List<User>> {
        return userRepository.searchUsers(username = query)
    }
}