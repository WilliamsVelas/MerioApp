package com.example.merioapp.addSell.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merioapp.GlobalVar
import com.example.merioapp.ui.domain.entity.Sell
import com.example.merioapp.ui.domain.usecase.client.ClientUseCase
import com.example.merioapp.ui.domain.usecase.product.ProductUseCase
import com.example.merioapp.ui.domain.usecase.sell.SellUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSellViewModel @Inject constructor(
    private val useCase: SellUseCase,
    private val useCaseClient: ClientUseCase,
    private val useCaseProduct: ProductUseCase
) : ViewModel() {
    var name_client by mutableStateOf("")
    var client_email by mutableStateOf("")
    var client_phone by mutableStateOf("")
    var identification_client by mutableStateOf("")
    var description by mutableStateOf("")
    var provider by mutableStateOf("")
    var name_product by mutableStateOf("")
    var serial_product by mutableStateOf("")
    var price_product by mutableStateOf("")
    var profit_product by mutableStateOf("")
    var client_id by mutableStateOf(0)
    var product_id by mutableStateOf(0)
    var profit_type by mutableStateOf("")

    var clients = useCaseClient.getClients()

    var products = useCaseProduct.getProducts()

    var expandedMenu by mutableStateOf(false)

    //SELECTOR OF PROFIT TYPE
    var profitTypeSelected by mutableStateOf("")
    val profitTypeList = listOf("$", "Bs.")


    fun addSell() = viewModelScope.launch {
        selectProfitType()
        useCase.insertSell(
            Sell(
                client_id = client_id,
                product_id = product_id,
                name_client = name_client,
                client_email = client_email,
                client_phone = client_phone,
                identification_client = identification_client,
                description = description,
                provider = provider,
                name_product = name_product,
                serial_product = serial_product,
                price_product = price_product.toFloat(),
                profit_product = profit_product.toFloat(),
                profit_type = profitTypeSelected
            )
        )
    }

    fun selectProfitType() = viewModelScope.launch {
        if (profitTypeSelected == "Usd.") {
            GlobalVar.isDolar = true
        } else {
            GlobalVar.isDolar = false
        }
    }
}