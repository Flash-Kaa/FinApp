package com.flasska.finapp.ui.elements.dialog.statistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.flasska.findomain.usecase.GetAverageUseCase
import com.flasska.findomain.usecase.GetMonthExpensesUseCase
import com.flasska.findomain.usecase.GetSumUseCase
import com.flasska.findomain.usecase.GetTypeToSumUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

internal class StatisticViewModel(
    private val getMonthExpensesUseCase: GetMonthExpensesUseCase,
    private val getTypeToSumUseCase: GetTypeToSumUseCase,
    private val getAverageUseCase: GetAverageUseCase,
    private val getSumUseCase: GetSumUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(StatisticDialogState())
    val state = _state.asStateFlow()

    fun updateDate(date: LocalDate) {
        viewModelScope.launch(Dispatchers.IO) {
            val expenses = getMonthExpensesUseCase(date)

            val average = getAverageUseCase(expenses)
            val sum = getSumUseCase(expenses)
            val typeToSum = getTypeToSumUseCase(expenses)
            val displayMonth = date.month.getDisplayName(TextStyle.FULL, Locale("ru"))

            _state.update {
                it.copy(
                    displayMonth = displayMonth,
                    sum = sum,
                    average = average,
                    typeToSum = typeToSum
                )
            }
        }
    }

    class FactoryWrapper(
        private val getMonthExpensesUseCase: GetMonthExpensesUseCase,
        private val getTypeToSumUseCase: GetTypeToSumUseCase,
        private val getAverageUseCase: GetAverageUseCase,
        private val getSumUseCase: GetSumUseCase
    ) {
        inner class Factory : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return StatisticViewModel(
                    getMonthExpensesUseCase = getMonthExpensesUseCase,
                    getTypeToSumUseCase = getTypeToSumUseCase,
                    getAverageUseCase = getAverageUseCase,
                    getSumUseCase = getSumUseCase
                ) as T
            }
        }
    }
}