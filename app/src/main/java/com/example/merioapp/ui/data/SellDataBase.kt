package com.example.merioapp.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.merioapp.ui.domain.entity.Client
import com.example.merioapp.ui.domain.entity.Sell
import com.example.merioapp.ui.domain.repository.Repository

@Database(entities = [Sell::class, Client::class], exportSchema = false, version = 1)
abstract class SellDataBase : RoomDatabase() {
    abstract fun sellDao(): Repository
}
