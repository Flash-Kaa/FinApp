package com.flasska.findomain.usecase

import com.flasska.findomain.entity.Expense

class GetTypeToSumUseCase {
    operator fun invoke(expenses: List<Expense>): Map<Expense.Type, Float> {
        return expenses.groupBy { it.type }
            .mapValues {
                it.value.sumOf { it.value.toDouble() }.toFloat()
            }
    }
}