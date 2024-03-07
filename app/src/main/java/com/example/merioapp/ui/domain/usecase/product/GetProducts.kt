package com.example.merioapp.ui.domain.usecase.product

import com.example.merioapp.ui.data.RepositoryImp
import javax.inject.Inject

class GetProducts @Inject constructor(private val repositoryImp: RepositoryImp) {
    operator fun invoke() = repositoryImp.getProduct()
}