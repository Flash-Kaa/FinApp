package com.flasska.finapp.ui.screens.dayscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.flasska.findomain.usecase.GetDayStatisticUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

internal class DayScreenViewModel(
    date: LocalDate,

    private val getDayStatisticUseCase: GetDayStatisticUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DayScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            updateByDate(date)
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
            val date = state.value.date.plusDays(1)
            updateByDate(date)
        }
    }

    private fun toPrevDay() {
        viewModelScope.launch(Dispatchers.IO) {
            val date = state.value.date.minusDays(1)
            updateByDate(date)
        }
    }

    private suspend fun DayScreenViewModel.updateByDate(date: LocalDate) {
        val list = getDayStatisticUseCase(date)
        _state.update { it.copy(date = date, expenses = list) }
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