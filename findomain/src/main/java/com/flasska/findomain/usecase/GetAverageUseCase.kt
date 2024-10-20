package com.flasska.findomain.usecase

import com.flasska.findomain.entity.Expense

class GetAverageUseCase {
    operator fun invoke(expenses: List<Expense>): Float {
        if (expenses.isEmpty()) {
            return 0f
        }

        return expenses.map { it.value }.average().toFloat()
    }
}