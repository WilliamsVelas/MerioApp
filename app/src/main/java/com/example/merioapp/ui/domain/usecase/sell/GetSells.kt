package com.example.merioapp.ui.domain.usecase.sell

import com.example.merioapp.ui.data.RepositoryImp
import com.example.merioapp.ui.domain.entity.Sell
import javax.inject.Inject

class GetSells @Inject constructor(private val repositoryImp: RepositoryImp) {
    operator fun invoke() = repositoryImp.getSells()
}