package com.parcial2.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.parcial2.data.ProductRepository
import com.parcial2.model.Product

class ProductDetailViewModel(productId: Int) : ViewModel() {
    private val repository = ProductRepository()

    private val _productState = mutableStateOf<Product?>(
        repository.getProducts().firstOrNull { it.id == productId }
    )
    val productState: State<Product?> = _productState
}

class ProductDetailViewModelFactory(private val productId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductDetailViewModel(productId) as T
        }
        throw IllegalArgumentException("No se puede crear instancia de: ${modelClass.name}")
    }
}
