package com.example.merioapp.addSell.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merioapp.ui.domain.entity.Sell
import com.example.merioapp.ui.domain.usecase.sell.SellUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSellViewModel @Inject constructor(private val useCase: SellUseCase) : ViewModel() {
    var name_client by mutableStateOf("")
    var identification_client by mutableStateOf("")
    var description by mutableStateOf("")
    var provider by mutableStateOf("")
    var name_product by mutableStateOf("")
    var serial_product by mutableStateOf("")
    var price_product by mutableStateOf("")
    var profit_product by mutableStateOf("")

    fun addSell() = viewModelScope.launch {
        useCase.insertSell(
            Sell(
                name_client = name_client,
                identification_client = identification_client,
                description = description,
                provider = provider,
                name_product = name_product,
                serial_product = serial_product,
                price_product = price_product.toFloat(),
                profit_product = profit_product.toFloat()
            )
        )
    }
}