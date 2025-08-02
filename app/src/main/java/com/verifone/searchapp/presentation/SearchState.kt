package com.verifone.searchapp.presentation

import com.verifone.searchapp.domain.Product
import com.verifone.searchapp.domain.User

sealed class SearchState {
        object Idle : SearchState()
        object Loading : SearchState()
        data class SuccessUser(val users: List<User>) : SearchState()
        data class SuccessProd(val prods: List<Product>) : SearchState()
        data class Error(val message: String) : SearchState()
    }