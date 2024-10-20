package com.flasska.finapp.ui.screens.dayscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.flasska.findomain.usecase.GetDayStatisticUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

internal class DayScreenViewModel(
    date: LocalDate,

    private val getDayStatisticUseCase: GetDayStatisticUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DayScreenState(date = date))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                val expenses = getDayStatisticUseCase(state.value.date)
                _state.update { it.copy(expenses = expenses) }
                delay(500)
            }
        }
    }

    fun getEvent(event: DayScreenEvent) {
        when (event) {
            DayScreenEvent.NextDay -> toNextDay()
            DayScreenEvent.PreviousDay -> toPrevDay()
        }
    }

    private fun toNextDay() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(date = it.date.plusDays(1)) }
        }
    }

    private fun toPrevDay() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(date = it.date.minusDays(1)) }
        }
    }

    class FactoryWrapper(
        private val getDayStatisticUseCase: GetDayStatisticUseCase
    ) {
        inner class Factory(
            private val date: LocalDate
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DayScreenViewModel(date, getDayStatisticUseCase) as T
            }
        }
    }
}