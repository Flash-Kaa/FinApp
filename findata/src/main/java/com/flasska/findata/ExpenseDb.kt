package com.flasska.findata

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseDb(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("id") val id: Int,
    @ColumnInfo("date_time") val dateTime: Long,
    @ColumnInfo("value") val value: Float,
    @Embedded val type: ExpenseType
) {
    data class ExpenseType(
        val id: Int,
        val name: String,
        val color: Long
    )
}
