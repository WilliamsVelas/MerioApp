package com.example.merioapp.ui.data

import com.example.merioapp.ui.domain.entity.Client
import com.example.merioapp.ui.domain.entity.Sell
import com.example.merioapp.ui.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val dao: Repository) : Repository {

    //SELL

    override suspend fun insertSell(sell: Sell) = dao.insertSell(sell = sell)
    override suspend fun updateSell(sell: Sell) = dao.updateSell(sell = sell)
    override suspend fun deteleSell(sell: Sell) = dao.deteleSell(sell = sell)
    override fun getSells(): Flow<List<Sell>> = dao.getSells()

    override suspend fun getByIdSell(id: Int): Sell {
        TODO("Not yet implemented")
    }

    //CLIENT

    override suspend fun insertClient(client: Client) = dao.insertClient(client = client)
    override suspend fun updateClient(client: Client) = dao.updateClient(client = client)
    override suspend fun deleteClient(client: Client) = dao.deleteClient(client = client)

    override fun getClients(): Flow<List<Client>> = dao.getClients()

    override suspend fun getByIdClient(id: Int): Client {
        TODO("Not yet implemented")
    }
}