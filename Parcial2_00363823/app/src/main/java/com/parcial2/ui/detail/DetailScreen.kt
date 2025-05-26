import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.parcial2.viewmodel.CartViewModel
import com.parcial2.viewmodel.ProductDetailViewModel
import com.parcial2.viewmodel.ProductDetailViewModelFactory

@Composable
fun ProductDetailScreen(
    productId: Int,
    viewModel: ProductDetailViewModel = viewModel(factory = ProductDetailViewModelFactory(productId)),
    cartViewModel: CartViewModel = viewModel()
) {
    val productState by viewModel.productState

    if (productState == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Producto no encontrado",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        val product = productState!!

        // Verificamos si el producto ya está en el carrito
        val isInCart = cartViewModel.cartItems.any { it.product.id == product.id }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = product.name, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                Text(text = "Categoría: ${product.category}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Precio: $${product.price}", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (!isInCart) {
                        cartViewModel.addToCart(product)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isInCart // Deshabilita el botón si ya está en el carrito
            ) {
                if (isInCart) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.checkbox_on_background),
                        contentDescription = "Producto en carrito"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("En el carrito")
                } else {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Agregar al carrito")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Agregar al carrito")
                }
            }
        }
    }
}

