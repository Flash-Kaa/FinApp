package com.flasska.findata.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ExpenseDb(
    @Embedded var expanse: ExpenseValueDb,
    @Relation(
        parentColumn = "type_id",
        entityColumn = "id"
    ) var type: ExpenseTypeDb
)