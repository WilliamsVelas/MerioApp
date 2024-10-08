package com.example.merioapp.addClient.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merioapp.ui.domain.entity.Client
import com.example.merioapp.ui.domain.entity.Sell
import com.example.merioapp.ui.domain.usecase.client.ClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddClientViewModel @Inject constructor(private val useCase: ClientUseCase) : ViewModel() {
    var name_client by mutableStateOf("")
    var identification_client by mutableStateOf("")
    var description by mutableStateOf("")
    var email by mutableStateOf("")
    var phone_number by mutableStateOf("")

    var expandedMenu by mutableStateOf(false)

    //PREFIX OPTIONS FOR identification_client INPUT
    var selectedPrefix by mutableStateOf("")
    val idenditificationPrefix = listOf("V-", "J-", "E-")


    fun addClient() = viewModelScope.launch {
        useCase.insertClient(
            Client(
                name_client = name_client,
                identification_client = selectedPrefix + identification_client,
                description = description,
                email = email,
                phone_number = phone_number
            )
        )
    }

    fun cleanInputs() = viewModelScope.launch {
        name_client = ""
        email = ""
        phone_number = ""
        description = ""
        identification_client = ""
    }
}