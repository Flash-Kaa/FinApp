package com.flasska.findomain.usecase

import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.StatisticRepository
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset

    class GetDayStatisticUseCase(
        private val repository: StatisticRepository
) {
    suspend operator fun invoke(
        day: Int,
        month: Month,
        year: Int
    ): List<Expense> {
        val dateTimeStart = LocalDateTime.of(year, month, day, 0, 0).toEpochSecond(ZoneOffset.UTC)
        val dateTimeEnd = LocalDateTime.of(year, month, day + 1, 0, 0).toEpochSecond(ZoneOffset.UTC)

        return repository.getInfoInRage(dateTimeStart, dateTimeEnd)
    }
}