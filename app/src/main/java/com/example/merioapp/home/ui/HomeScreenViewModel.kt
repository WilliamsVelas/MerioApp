package com.example.merioapp.home.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merioapp.GlobalVar
import com.example.merioapp.ui.domain.entity.Sell
import com.example.merioapp.ui.domain.usecase.sell.SellUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val useCase: SellUseCase) : ViewModel() {

    val sells = useCase.getSells()

    var profitCalculated = 0.0f

    val resultList = mutableListOf<Float>()

    var totalProfit = 0.0f

    fun calcProfitByType(profit: Float, profitType: String): Float {
        if (GlobalVar.isDolar == true) {
            if (profitType == "$") profitCalculated = profit;

            if (profitType == "Bs.") profitCalculated = profit / GlobalVar.amountConvertion

        } else if (GlobalVar.isDolar == false) {
            if (profitType == "Bs.") profitCalculated = profit

            if (profitType == "$") profitCalculated = profit * GlobalVar.amountConvertion
        }

        profitCalculated = String.format("%.2f", profitCalculated).toFloat()

        if (profitCalculated != 0.0f) {
            resultList.add(profitCalculated)
        }

        totalProfit = resultList.sum()

        return profitCalculated
    }


}