package com.example.merioapp.products.ui

import androidx.lifecycle.ViewModel
import com.example.merioapp.ui.domain.usecase.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(private val useCase: ProductUseCase) :
    ViewModel() {

    val products = useCase.getProducts()

}