package com.parcial2.ui.navigation

import ProductDetailScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.parcial2.ui.cart.CartScreen
import com.parcial2.ui.screens.HomeScreen

import com.parcial2.viewmodel.CartViewModel
import com.parcial2.viewmodel.ProductDetailViewModelFactory

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel() // ✅ ViewModel compartido

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // 🏠 Pantalla principal
        composable("home") {
            HomeScreen(
                onProductClick = { product ->
                    navController.navigate("detail/${product.id}")
                },
                onCartClick = {
                    navController.navigate("cart")
                },
                cartViewModel = cartViewModel
            )
        }

        // 🔍 Detalle del producto
        composable(
            route = "detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(
                productId = productId,
                viewModel = viewModel(factory = ProductDetailViewModelFactory(productId)),
                cartViewModel = cartViewModel
            )
        }

        // 🛒 Pantalla del carrito
        composable("cart") {
            CartScreen(cartViewModel = cartViewModel)
        }
    }
}
