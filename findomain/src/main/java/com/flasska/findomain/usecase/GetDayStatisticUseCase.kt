package com.flasska.findomain.usecase

import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.StatisticRepository
import java.time.LocalDate
import java.time.ZoneOffset

class GetDayStatisticUseCase(
    private val repository: StatisticRepository
) {
    suspend operator fun invoke(
        date: LocalDate
    ): List<Expense> {
        val dateTimeStart = date.atStartOfDay()

        val dateTimeEnd = dateTimeStart.plusDays(1)

        val start = dateTimeStart.toEpochSecond(ZoneOffset.UTC)
        val end = dateTimeEnd.toEpochSecond(ZoneOffset.UTC)

        return repository.getInfoInRage(start, end)
    }
}