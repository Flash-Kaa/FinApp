package com.flasska.findata.repository

import com.flasska.findata.db.ExpenseDao
import com.flasska.findata.mappers.ExpenseMapperUtils.convert
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.repository.Repository

class ExpenseRepository(
    private val dao: ExpenseDao
) : Repository<Expense> {
    override suspend fun getAll(): List<Expense> {
        return dao.getAll().map { it.convert() }
    }

    override suspend fun delete(value: Expense) {
        dao.deleteExpenseValue(value.convert().expanse)
    }

    override suspend fun add(value: Expense) {
        dao.addExpenseValue(value.convert().expanse)
    }
}