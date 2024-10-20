package com.flasska.findomain.usecase

import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.StatisticRepository
import java.time.LocalDate

class GetMonthExpensesUseCase(
    private val repository: StatisticRepository
) {
    suspend operator fun invoke(date: LocalDate): List<Expense> {
        val dateTimeStart = LocalDate.of(date.year, date.month, 1)

        val start = dateTimeStart.toEpochDay()
        val end = dateTimeStart.plusMonths(1).toEpochDay()

        return repository.getInfoInRage(start, end)
    }
}