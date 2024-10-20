package com.flasska.findomain.usecase

import com.flasska.findomain.entity.Expense

class GetSumUseCase {
    operator fun invoke(expenses: List<Expense>) = expenses.map { it.value }.sum()
}