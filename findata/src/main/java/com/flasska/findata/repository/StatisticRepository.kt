package com.flasska.findata.repository

import com.flasska.findata.db.ExpenseDao
import com.flasska.findata.mappers.ExpenseMapperUtils.convert
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.StatisticRepository

internal class StatisticRepository(
    private val dao: ExpenseDao
) : StatisticRepository {
    override suspend fun getInfoInRage(start: Long, end: Long): List<Expense> {
        return dao.getAllInDateRange(start, end).map { it.convert() }
    }
}