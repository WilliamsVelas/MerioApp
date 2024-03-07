package com.example.merioapp.ui.di

import android.content.Context
import androidx.room.Room
import com.example.merioapp.ui.data.RepositoryImp
import com.example.merioapp.ui.data.SellDataBase
import com.example.merioapp.ui.domain.usecase.client.ClientUseCase
import com.example.merioapp.ui.domain.usecase.client.DeleteClient
import com.example.merioapp.ui.domain.usecase.client.GetClients
import com.example.merioapp.ui.domain.usecase.client.InsertClient
import com.example.merioapp.ui.domain.usecase.client.UpdateClient
import com.example.merioapp.ui.domain.usecase.product.DeleteProduct
import com.example.merioapp.ui.domain.usecase.product.GetProducts
import com.example.merioapp.ui.domain.usecase.product.InsertProduct
import com.example.merioapp.ui.domain.usecase.product.ProductUseCase
import com.example.merioapp.ui.domain.usecase.product.UpdateProduct
import com.example.merioapp.ui.domain.usecase.sell.DeleteSell
import com.example.merioapp.ui.domain.usecase.sell.GetSells
import com.example.merioapp.ui.domain.usecase.sell.InsertSell
import com.example.merioapp.ui.domain.usecase.sell.SellUseCase
import com.example.merioapp.ui.domain.usecase.sell.UpdateSell
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SellModule {

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): SellDataBase = Room.databaseBuilder(
        context = context,
        klass = SellDataBase::class.java,
        name = "SellDB"
    ).build()

    @Provides
    fun providerDao(dao: SellDataBase) = dao.sellDao()

    @Provides
    fun providerSellUseCase(repositoryImp: RepositoryImp) = SellUseCase(
        getSells = GetSells(repositoryImp),
        updateSell = UpdateSell(repositoryImp),
        insertSell = InsertSell(repositoryImp),
        deleteSell = DeleteSell(repositoryImp)
    )

    @Provides
    fun providerClientUseCase(repositoryImp: RepositoryImp) = ClientUseCase(
        getClients = GetClients(repositoryImp),
        insertClient = InsertClient(repositoryImp),
        updateClient = UpdateClient(repositoryImp),
        deleteClient = DeleteClient(repositoryImp)
    )

    @Provides
    fun providerProductUseCase(repositoryImp: RepositoryImp) = ProductUseCase(
        getProducts = GetProducts(repositoryImp),
        insertProduct = InsertProduct(repositoryImp),
        updateProduct = UpdateProduct(repositoryImp),
        deleteProduct = DeleteProduct(repositoryImp)
    )
}