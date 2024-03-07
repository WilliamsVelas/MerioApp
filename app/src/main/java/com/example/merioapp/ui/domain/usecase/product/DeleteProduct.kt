package com.example.merioapp.ui.domain.usecase.product

import com.example.merioapp.ui.data.RepositoryImp
import com.example.merioapp.ui.domain.entity.Product
import javax.inject.Inject

class DeleteProduct @Inject constructor(private val repositoryImp: RepositoryImp) {

    suspend operator fun invoke(product: Product) = repositoryImp.deteleProduct(product)

}