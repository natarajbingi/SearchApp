package com.verifone.searchapp.domain

import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend operator fun invoke(query: String): Result<List<Product>> {
        return dataRepository.searchProducts(query)
    }
}