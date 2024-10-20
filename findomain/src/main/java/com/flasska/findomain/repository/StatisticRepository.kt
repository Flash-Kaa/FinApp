package com.flasska.findomain.repository

import com.flasska.findomain.entity.Expense

interface StatisticRepository {
    suspend fun getInfoInRage(start: Long, end: Long): List<Expense>
}