package com.example.merioapp.addProduct.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merioapp.ui.domain.entity.Product
import com.example.merioapp.ui.domain.usecase.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val useCase: ProductUseCase) : ViewModel() {
    var provider by mutableStateOf("")
    var name_product by mutableStateOf("")
    var serial_product by mutableStateOf("")
    var price_product by mutableStateOf("")

    fun addProduct() = viewModelScope.launch {
        useCase.insertProduct(
            Product(
                provider = provider,
                name_product = name_product,
                serial_product = serial_product,
                price_product = price_product.toFloat(),
            )
        )
    }

    fun cleanInputs() = viewModelScope.launch {
        provider = ""
        name_product = ""
        serial_product = ""
        price_product = ""
    }
}
