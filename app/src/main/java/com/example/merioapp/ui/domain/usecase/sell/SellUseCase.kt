package com.example.merioapp.ui.domain.usecase.sell

data class SellUseCase(
    val insertSell: InsertSell,
    val deleteSell: DeleteSell,
    val updateSell: UpdateSell,
    val getSells: GetSells
)

