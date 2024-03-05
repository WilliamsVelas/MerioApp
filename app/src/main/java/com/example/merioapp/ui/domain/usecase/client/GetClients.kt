package com.example.merioapp.ui.domain.usecase.client

import com.example.merioapp.ui.data.RepositoryImp
import javax.inject.Inject

class GetClients @Inject constructor(private val repositoryImp: RepositoryImp){
    operator fun invoke() = repositoryImp.getClients()
}