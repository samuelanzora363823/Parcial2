package com.parcial2.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.parcial2.data.CartItem
import com.parcial2.model.Product

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    fun addToCart(product: Product) {
        val index = _cartItems.indexOfFirst { it.product.id == product.id }
        if (index >= 0) {
            val item = _cartItems[index]
            val updatedItem = item.copy(quantity = item.quantity + 1)
            _cartItems[index] = updatedItem
        } else {
            _cartItems.add(CartItem(product, 1))
        }
    }

    fun removeFromCart(product: Product) {
        val index = _cartItems.indexOfFirst { it.product.id == product.id }
        if (index >= 0) {
            val item = _cartItems[index]
            if (item.quantity > 1) {
                val updatedItem = item.copy(quantity = item.quantity - 1)
                _cartItems[index] = updatedItem
            } else {
                _cartItems.removeAt(index)
            }
        }
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun getTotalPrice(): Double {
        return _cartItems.sumOf { it.product.price * it.quantity }
    }
}
