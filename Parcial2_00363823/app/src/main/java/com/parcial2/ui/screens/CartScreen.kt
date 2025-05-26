package com.parcial2.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.parcial2.viewmodel.CartViewModel

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val cartItems = cartViewModel.cartItems

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Carrito de Compras",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            if (cartItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tu carrito está vacío", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(cartItems) { cartItem ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Imagen del producto
                            Image(
                                painter = painterResource(id = cartItem.product.imageRes),
                                contentDescription = cartItem.product.name,
                                modifier = Modifier
                                    .size(64.dp)
                                    .padding(end = 8.dp),
                                contentScale = ContentScale.Crop
                            )

                            // Nombre y precio
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = cartItem.product.name,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = "$${cartItem.product.price}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            // Botones para controlar cantidad
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = { cartViewModel.removeFromCart(cartItem.product) },
                                    modifier = Modifier.size(32.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text("-", style = MaterialTheme.typography.bodyLarge)
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = cartItem.quantity.toString(),
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.width(24.dp),
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Button(
                                    onClick = { cartViewModel.addToCart(cartItem.product) },
                                    modifier = Modifier.size(32.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text("+", style = MaterialTheme.typography.bodyLarge)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total: $${cartViewModel.getTotalPrice()}",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
