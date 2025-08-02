package com.verifone.searchapp.domain

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
)


data class Product(
    val id: String,
    val name: String,
    val description: String?,
    val dateAdded: String,
    val regularPrice: String,
    val imageUrl: String?
){
    override fun toString(): String {
        return "Product(id='$id', name='$name', description=$description, dateAdded='$dateAdded', regularPrice='$regularPrice', imageUrl=$imageUrl)"
    }
}