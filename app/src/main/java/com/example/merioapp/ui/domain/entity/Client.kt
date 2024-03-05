package com.example.merioapp.ui.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class Client(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name_client: String = "",
    val identification_client: String = "",
    val description: String = "",
    val email: String = "",
    val phone_number: String = "",
)