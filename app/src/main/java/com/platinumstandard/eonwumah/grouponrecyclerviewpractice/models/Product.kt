package com.platinumstandard.eonwumah.grouponrecyclerviewpractice.models

data class Product(
    var name: String,
    var category: Category,
    var price: Double? = null,
    var rating: Double
)