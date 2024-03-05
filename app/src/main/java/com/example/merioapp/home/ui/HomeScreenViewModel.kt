package com.example.merioapp.home.ui

import androidx.lifecycle.ViewModel
import com.example.merioapp.ui.domain.usecase.sell.SellUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val useCase: SellUseCase): ViewModel() {

    val sells = useCase.getSells()

}