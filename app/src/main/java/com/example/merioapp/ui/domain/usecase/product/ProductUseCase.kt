package com.example.merioapp.ui.domain.usecase.product

data class ProductUseCase(
    val insertProduct: InsertProduct,
    val deleteProduct: DeleteProduct,
    val updateProduct: UpdateProduct,
    val getProducts: GetProducts
)