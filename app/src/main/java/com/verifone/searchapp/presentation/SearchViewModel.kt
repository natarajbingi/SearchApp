package com.verifone.searchapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verifone.searchapp.domain.SearchProductsUseCase
import com.verifone.searchapp.domain.SearchUsersUseCase
import com.verifone.searchapp.domain.User
import com.verifone.searchapp.presentation.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUserUseCase: SearchUsersUseCase,
    private val searchProdUseCase: SearchProductsUseCase
) : ViewModel() {
    val TAG = "SearchViewModel ->"

    private val _searchUserState = MutableStateFlow<SearchState>(SearchState.Idle)
    val searchUserState: StateFlow<SearchState> = _searchUserState.asStateFlow()

    private val _searchProdState = MutableStateFlow<SearchState>(SearchState.Idle)
    val searchProdState: StateFlow<SearchState> = _searchProdState.asStateFlow()

    private var searchJob: Job? = null

    fun performSearch(query: String) {
        if (query.isBlank() || query.length < 2) {
            _searchProdState.value = SearchState.Idle
            return
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            _searchProdState.value = SearchState.Loading

            val result = searchProdUseCase(query)
            _searchProdState.value = if (result.isSuccess) {
                val users = result.getOrNull() ?: emptyList()
                Log.d(TAG, "Search successful, query is $query and  found ${users.size} users.")
                SearchState.SuccessProd(users)
            } else {
                val errorMessage = result.exceptionOrNull()?.message ?: "An unknown error occurred"
                Log.e(TAG, "Search failed: $errorMessage")
                SearchState.Error(errorMessage)
            }
        }
    }
}