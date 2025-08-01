package com.verifone.searchapp.presentation

import com.verifone.searchapp.domain.User

sealed class SearchState {
        object Idle : SearchState()
        object Loading : SearchState()
        data class Success(val users: List<User>) : SearchState()
        data class Error(val message: String) : SearchState()
    }