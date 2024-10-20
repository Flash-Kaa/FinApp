package com.flasska.findomain.usecase

import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.StatisticRepository
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset

class GetMonthStatisticUseCase(
    private val repository: StatisticRepository
) {
    suspend operator fun invoke(month: Month, year: Int): List<Expense> {
        val dateTimeStart = LocalDateTime.of(year, month, 1, 0, 0).toEpochSecond(ZoneOffset.UTC)
        val dateTimeEnd = if (month == Month.DECEMBER) {
            LocalDateTime.of(year, month, 1, 0, 0)
        } else {
            LocalDateTime.of(year, month.plus(1), 1, 0, 0)
        }.toEpochSecond(ZoneOffset.UTC)


        return repository.getInfoInRage(dateTimeStart, dateTimeEnd)
    }
}