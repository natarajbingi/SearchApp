package com.verifone.searchapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.verifone.searchapp.databinding.ActivitySearchBinding
import com.verifone.searchapp.presentation.SearchState
import com.verifone.searchapp.presentation.SearchViewModel
import com.verifone.searchapp.presentation.adapter.ProdAdapter
import com.verifone.searchapp.presentation.adapter.UserAdapter
import com.verifone.searchapp.utils.Logs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    val TAG = "SearchActivity"
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding
    private val searchAdapter = ProdAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logs.d(TAG, "onCreate")
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.usersRecyclerView.adapter = searchAdapter

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {}

            override fun beforeTextChanged(
                char: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(char: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.performSearch(query = char.toString())
            }
        })

        lifecycleScope.launch {
            viewModel.searchProdState.collect { state ->
                Logs.d(TAG, "state: $state")
                when (state) {
                    is SearchState.Idle -> {
                        // Handle initial state
                        binding.progressBar.visibility = View.GONE
                        binding.statusTextView.text = "Type to search..."
                        binding.statusTextView.visibility = View.VISIBLE
//                        binding.usersRecyclerView.visibility = View.GONE
                        searchAdapter.submitList(emptyList())
                    }

                    is SearchState.Loading -> {
                        // Handle loading state
                        binding.progressBar.visibility = View.VISIBLE
                        binding.statusTextView.visibility = View.GONE
                    }

                    is SearchState.SuccessUser -> {
                        /*binding.progressBar.visibility = View.GONE
                        binding.statusTextView.visibility =if (state.users.isEmpty()) View.VISIBLE else View.GONE
                        binding.statusTextView.text = "No users found"
                       searchAdapter.submitList(state.users)*/
                    }

                    is SearchState.SuccessProd -> {
                        binding.progressBar.visibility = View.GONE
                        binding.statusTextView.visibility = if (state.prods.isEmpty()) View.VISIBLE else View.GONE
                        binding.statusTextView.text = "No product found"
                        searchAdapter.submitList(state.prods)
                    }

                    is SearchState.Error -> {
                        // Handle error state
                        binding.progressBar.visibility = View.GONE
                        binding.statusTextView.visibility = View.VISIBLE
                        binding.statusTextView.text = "Error: ${state.message}"
                        searchAdapter.submitList(emptyList())
                    }
                }
            }
        }
    }
}