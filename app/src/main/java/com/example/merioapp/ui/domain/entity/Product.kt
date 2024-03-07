package com.example.merioapp.ui.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val provider: String = "",
    val name_product: String = "",
    val serial_product: String = "",
    val price_product: Float = 0.0f,
)