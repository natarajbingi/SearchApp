package com.verifone.searchapp.domain

import com.verifone.searchapp.utils.Logs
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    val TAG = "SearchProductsUseCase"
    suspend operator fun invoke(query: String): Result<List<Product>> {
        Logs.d(TAG, "invoke: $query dataRepository.searchProducts")
        return dataRepository.searchProducts(query)
    }
}