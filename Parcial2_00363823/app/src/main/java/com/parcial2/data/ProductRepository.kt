package com.parcial2.data

import com.parcial2.R
import com.parcial2.model.Product

class ProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Laptop Lenovo",
                category = "Tecnología",
                price = 2999.99,
                imageRes = R.drawable.fdfs
            ),
            Product(
                id = 2,
                name = "Zapatillas Nike",
                category = "Ropa",
                price = 159.50,
                imageRes = R.drawable.nike
            ),
            Product(
                id = 3,
                name = "Smartphone Samsung",
                category = "Tecnología",
                price = 1299.00,
                imageRes = R.drawable.samsumd
            ),
            Product(
                id = 4,
                name = "Mochila Escolar",
                category = "Accesorios",
                price = 89.90,
                imageRes = R.drawable.mochula
            ),
            Product(
                id = 5,
                name = "Audífonos Bluetooth",
                category = "Tecnología",
                price = 199.99,
                imageRes = R.drawable.audifonos
            ),
            Product(
                id = 6,
                name = "Camisa Casual",
                category = "Ropa",
                price = 49.99,
                imageRes = R.drawable.camisa
            ),
            Product(
                id = 7,
                name = "Reloj Inteligente",
                category = "Tecnología",
                price = 899.00,
                imageRes = R.drawable.reloj
            ),
            Product(
                id = 8,
                name = "Botella Térmica",
                category = "Accesorios",
                price = 39.90,
                imageRes = R.drawable.botella
            )
        )
    }
}
