package com.flasska.findata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {
    @Insert
    fun addExpense(value: ExpenseDb)

    @Delete
    fun deleteExpense(value: ExpenseDb)

    @Query("SELECT * FROM ExpenseDb")
    fun getAll(): List<ExpenseDb>

    @Query("SELECT * FROM EXPENSEDB WHERE date_time >= :startDate AND date_time <= :endDate")
    fun getAllInDateRange(startDate: Long, endDate: Long): List<ExpenseDb>
}