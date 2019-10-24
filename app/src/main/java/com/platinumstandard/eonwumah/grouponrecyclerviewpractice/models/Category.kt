package com.platinumstandard.eonwumah.grouponrecyclerviewpractice.models

enum class Category(val value: String) {
    MENS("Mens"),
    WOMENS("Womens"),
    HOME("Home"),
    JEWELRY("Jewelry"),
    KIDS("Kids"),
    ELECTRONICS("Electronics"),
    SPORTS("Sports");

    override fun toString() = value
}