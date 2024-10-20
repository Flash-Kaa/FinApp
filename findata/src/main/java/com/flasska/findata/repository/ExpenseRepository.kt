package com.flasska.findata.repository

import com.flasska.findata.db.ExpenseDao
import com.flasska.findata.mappers.ExpenseMapperUtils.convert
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update

class ExpenseRepository(
    private val dao: ExpenseDao
) : Repository<Expense> {
    private val _flow = MutableStateFlow<List<Expense>>(listOf())
    val flow = _flow.asSharedFlow()

    override suspend fun getAll(): Flow<List<Expense>> {
        val updateValue = dao.getAll().map { it.convert() }
        _flow.update { updateValue }

        return flow
    }

    override suspend fun delete(value: Expense) {
        dao.deleteExpenseValue(value.convert().expanse)
        _flow.update { it - value }
    }

    override suspend fun add(value: Expense) {
        dao.addExpenseValue(value.convert().expanse)
        _flow.update { it + value }
    }
}