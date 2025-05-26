package com.parcial2.model

import androidx.annotation.DrawableRes

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    @DrawableRes val imageRes: Int
)
