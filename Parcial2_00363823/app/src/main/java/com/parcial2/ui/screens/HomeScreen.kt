package com.parcial2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.parcial2.model.Product
import com.parcial2.ui.viewmodel.HomeViewModel
import com.parcial2.viewmodel.CartViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel(),
    onProductClick: (Product) -> Unit,
    onCartClick: () -> Unit // 🔹 Nuevo parámetro para manejar click al carrito
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCartClick, // 🔹 Aquí se navega al carrito
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrito",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchBar(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            ProductList(viewModel.products.value, onProductClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: HomeViewModel) {
    TextField(
        value = viewModel.searchText.value,
        onValueChange = { viewModel.onSearchTextChange(it) },
        placeholder = { Text("Buscar por nombre o categoría") },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar"
            )
        }
    )
}

@Composable
fun ProductList(products: List<Product>, onProductClick: (Product) -> Unit) {
    LazyColumn {
        items(products) { product ->
            ProductCard(product = product, onClick = { onProductClick(product) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Categoría: ${product.category}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Precio: $${product.price}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
