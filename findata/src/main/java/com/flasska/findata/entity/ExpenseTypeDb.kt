package com.flasska.findata.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseTypeDb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val color: Int
)