package com.verifone.searchapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.verifone.searchapp.R
import com.verifone.searchapp.databinding.ItemProductBinding
import com.verifone.searchapp.domain.Product


class ProdAdapter : ListAdapter<Product, ProdAdapter.ProdViewHolder>(ProdDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdViewHolder {
        // Inflate the item layout using its binding class
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ProdViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ProdViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root){
        // The bind function now uses the binding object to set data
        fun bind(product: Product) {
            binding.productNameTextView.text = product.name
            binding.productDescriptionTextView.text = product.description ?: "No description available"
            binding.productPriceTextView.text = "Price: ${product.regularPrice}"
            binding.productDateTextView.text = "Date: ${product.dateAdded}"

            // Use Glide to load the image from the URL
            Glide.with(binding.root.context)
                .load(product.imageUrl)
                .placeholder(R.drawable.ic_placeholder) // Add a placeholder drawable
                .error(R.drawable.ic_error) // Add an error drawable
                .into(binding.productImageView)
        }
    }
}
