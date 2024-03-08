package com.example.merioapp.ui.domain.usecase.sell

import com.example.merioapp.ui.data.RepositoryImp
import javax.inject.Inject

class GetSellsByClientId @Inject constructor(private val repositoryImp: RepositoryImp){
    suspend operator fun invoke(id: Int) = repositoryImp.getByIdClient(id)
}