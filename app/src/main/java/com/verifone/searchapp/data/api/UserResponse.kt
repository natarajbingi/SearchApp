package com.verifone.searchapp.data.api

// user results
data class UserResponse(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
)

// search results
data class ProductSearchResponse(
    val code: Int,
    val success: Boolean,
    val psdata: ProductData
)

data class ProductData(
    val products: List<ProductResponse>
)

data class ProductResponse(
    val id_product: String,
    val name: String,
    val description: String?,
    val date_add: String,
    val regular_price: String,
    // The 'cover' object contains the image URL
    val cover: CoverImageResponse?
)

data class CoverImageResponse(
    val url: String
)