package com.example.merioapp.home.ui

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

    var totalProfit = 0.0f

    fun calcProfitByType(profit: Float, profitType: String): Float {
        when (GlobalVar.isDolar) {
            true -> when (profitType) {
                "$" -> {
                    profitCalculated = profit;
                }

                "Bs." -> {
                    profitCalculated =
                        profit / GlobalVar.amountConvertion
                }
            }

            false -> when (profitType) {
                "Bs." -> {
                    profitCalculated = profit
                }

                "$" -> {
                    profitCalculated =
                        profit * GlobalVar.amountConvertion
                }
            }
        }

        profitCalculated = String.format("%.2f", profitCalculated).toFloat()

        return profitCalculated
    }
}