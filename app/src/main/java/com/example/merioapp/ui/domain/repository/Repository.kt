package com.example.merioapp.ui.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.merioapp.ui.domain.entity.Client
import com.example.merioapp.ui.domain.entity.Sell
import kotlinx.coroutines.flow.Flow

@Dao
interface Repository {

    //SELLS

    @Insert
    suspend fun insertSell(sell: Sell)

    @Update
    suspend fun updateSell(sell: Sell)

    @Delete
    suspend fun deteleSell(sell: Sell)

    @Query("select * from sell")
    fun getSells(): Flow<List<Sell>>

    @Query("select * from sell where id =:id")
    suspend fun getByIdSell(id: Int): Sell

    //CLIENTS

    @Insert
    suspend fun insertClient(client: Client)

    @Update
    suspend fun updateClient(client: Client)

    @Delete
    suspend fun deleteClient(client: Client)

    @Query("select * from client")
    fun getClients(): Flow<List<Client>>


    @Query("select * from client where id =:id")
    suspend fun getByIdClient(id: Int): Client

    //PRODUCTS

}