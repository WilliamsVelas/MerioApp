package com.example.merioapp.ui.domain.usecase.client

data class ClientUseCase(
    val insertClient: InsertClient,
    val deleteClient: DeleteClient,
    val updateClient: UpdateClient,
    val getClients: GetClients
)