package com.flasska.findata.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseTypeDb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val color: Int
)