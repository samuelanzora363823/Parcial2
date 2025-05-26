package com.parcial2.data

import com.parcial2.model.Product

data class CartItem(
    val product: Product,
    var quantity: Int = 1
)