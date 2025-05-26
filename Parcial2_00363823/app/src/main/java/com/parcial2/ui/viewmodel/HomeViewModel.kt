package com.parcial2.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.parcial2.data.ProductRepository
import com.parcial2.model.Product

class HomeViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _products = mutableStateOf(repository.getProducts())
    val products: State<List<Product>> = _products

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        filterProducts()
    }

    private fun filterProducts() {
        _products.value = if (_searchText.value.isEmpty()) {
            repository.getProducts()
        } else {
            repository.getProducts().filter { product ->
                product.name.contains(_searchText.value, ignoreCase = true) ||
                        product.category.contains(_searchText.value, ignoreCase = true)
            }
        }
    }
}
