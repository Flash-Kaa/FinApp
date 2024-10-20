package com.flasska.findata.repository

import com.flasska.findata.db.ExpenseDao
import com.flasska.findata.mappers.ExpenseMapperUtils.convert
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update

class ExpenseTypeRepository(
    private val dao: ExpenseDao
) : Repository<Expense.Type> {
    private val _flow = MutableStateFlow<List<Expense.Type>>(listOf())
    val flow = _flow.asSharedFlow()

    override suspend fun getAll(): Flow<List<Expense.Type>> {
        val updateValue = dao.getTypes().map { it.convert() }
        _flow.update { updateValue }

        return flow
    }

    override suspend fun delete(value: Expense.Type) {
        dao.deleteExpenseType(value.convert())
        getAll()
    }

    override suspend fun add(value: Expense.Type) {
        val converted = value.convert()
        dao.addExpenseType(converted)
        getAll()
    }
}