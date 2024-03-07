package com.example.merioapp.ui.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sell")
data class Sell(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val client_id: Int = 0,
    val product_id: Int = 0,
    val name_client: String = "",
    val identification_client: String = "",
    val description: String = "",
    val provider: String = "",
    val name_product: String = "",
    val serial_product: String = "",
    val price_product: Float = 0.0f,
    val profit_product: Float = 0.0f,
)
