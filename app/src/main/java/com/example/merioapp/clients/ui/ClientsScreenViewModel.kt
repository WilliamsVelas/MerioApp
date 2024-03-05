package com.example.merioapp.clients.ui

import androidx.lifecycle.ViewModel
import com.example.merioapp.ui.domain.usecase.client.ClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientsScreenViewModel @Inject constructor(private val useCase: ClientUseCase) : ViewModel() {

    val client = useCase.getClients()

}