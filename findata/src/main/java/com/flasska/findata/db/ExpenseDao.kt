package com.flasska.findata.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.flasska.findata.db.entity.ExpenseDb
import com.flasska.findata.db.entity.ExpenseTypeDb
import com.flasska.findata.db.entity.ExpenseValueDb

@Dao
interface ExpenseDao {
    @Insert
    fun addExpenseValue(value: ExpenseValueDb)

    @Insert
    fun addExpenseType(type: ExpenseTypeDb)

    @Delete
    fun deleteExpenseValue(value: ExpenseValueDb)

    @Delete
    fun deleteExpenseType(type: ExpenseTypeDb)

    @Transaction
    @Query("SELECT * FROM ExpenseValueDb")
    suspend fun getAll(): List<ExpenseDb>

    @Transaction
    @Query("SELECT * FROM ExpenseValueDb WHERE date_time >= :startDate AND date_time <= :endDate")
    suspend fun getAllInDateRange(startDate: Long, endDate: Long): List<ExpenseDb>

    @Transaction
    @Query("SELECT * FROM ExpenseTypeDb")
    suspend fun getTypes(): List<ExpenseTypeDb>
}