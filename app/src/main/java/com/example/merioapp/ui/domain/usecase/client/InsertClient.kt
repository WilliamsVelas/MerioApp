package com.example.merioapp.ui.domain.usecase.client

import com.example.merioapp.ui.data.RepositoryImp
import com.example.merioapp.ui.domain.entity.Client
import javax.inject.Inject

class InsertClient @Inject constructor(private val repositoryImp: RepositoryImp){
    suspend operator fun invoke(client: Client) = repositoryImp.insertClient(client)
}