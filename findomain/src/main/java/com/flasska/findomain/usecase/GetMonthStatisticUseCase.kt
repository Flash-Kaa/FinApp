package com.flasska.findomain.usecase

import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.StatisticRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class GetMonthStatisticUseCase(
    private val repository: StatisticRepository
) {
    suspend operator fun invoke(date: LocalDate): List<Expense> {
        val dateTimeStart = LocalDateTime.of(date.year, date.month, 1, 0, 0)
        val dateTimeEnd = dateTimeStart.plusMonths(1)

        val start = dateTimeStart.toEpochSecond(ZoneOffset.UTC)
        val end = dateTimeEnd.toEpochSecond(ZoneOffset.UTC)

        return repository.getInfoInRage(start, end)
    }
}