package com.example.merioapp.clientProfile.ui

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merioapp.ui.domain.entity.Sell
import com.example.merioapp.ui.domain.usecase.sell.SellUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientProfileViewModel @Inject constructor(
    private val useCase: SellUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var name_client by mutableStateOf("")
    var identification_client by mutableStateOf("")
    var email by mutableStateOf("")
    var phone_number by mutableStateOf("")

    val id_client = savedStateHandle.get<Int>(key = "id")

    private val _sellByIdClient = MutableStateFlow<List<Sell>>(emptyList())
    val sellByIdClient: StateFlow<List<Sell>> = _sellByIdClient

    fun getSellByClientId(idClient: Int) {
        viewModelScope.launch {
            useCase.getSellsByClientId(idClient).collect { sells ->
                _sellByIdClient.value = sells

                if (sells.isNotEmpty()) {
                    val sell = sells[0]

                    Log.i("SELL1", "${sell}")

                    name_client = sell.name_client
                    identification_client = sell.identification_client
                    email = sell.client_email
                    phone_number = sell.client_phone
                }

            }
        }
    }

    init {
        getSellByClientId(id_client!!)
    }
}