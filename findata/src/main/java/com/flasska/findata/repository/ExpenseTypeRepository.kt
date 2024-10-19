package com.flasska.findata.repository

import com.flasska.findata.db.ExpenseDao
import com.flasska.findata.mappers.ExpenseMapperUtils.convert
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.Repository

class ExpenseTypeRepository(
    private val dao: ExpenseDao
): Repository<Expense.Type> {
    override suspend fun getAll(): List<Expense.Type> {
        return dao.getTypes().map { it.convert() }
    }

    override suspend fun delete(value: Expense.Type) {
        dao.deleteExpenseType(value.convert())
    }

    override suspend fun add(value: Expense.Type) {
        dao.addExpenseType(value.convert())
    }
}