package com.flasska.findata.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ExpenseTypeDb::class,
            parentColumns = ["id"],
            childColumns = ["type_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExpenseValueDb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("date_time") val dateTime: Long,
    val value: Float,
    @ColumnInfo("type_id") val typeId: Int
)
