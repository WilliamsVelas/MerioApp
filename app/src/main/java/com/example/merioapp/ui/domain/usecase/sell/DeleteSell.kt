package com.example.merioapp.ui.domain.usecase.sell

import com.example.merioapp.ui.data.RepositoryImp
import com.example.merioapp.ui.domain.entity.Sell
import javax.inject.Inject

class DeleteSell  @Inject constructor(private val repositoryImp: RepositoryImp) {
    suspend operator fun invoke(sell: Sell) = repositoryImp.deteleSell(sell)
}