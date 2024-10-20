package com.flasska.findomain.usecase

import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.StatisticRepository
import java.time.LocalDate

class GetDayExpensesUseCase(
    private val repository: StatisticRepository
) {
    suspend operator fun invoke(
        date: LocalDate
    ): List<Expense> {
        val start = date.toEpochDay()

        return repository.getInfoInRage(start, start)
    }
}