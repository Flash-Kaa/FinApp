package com.flasska.findata.mappers

import com.flasska.findata.db.entity.ExpenseDb
import com.flasska.findata.db.entity.ExpenseTypeDb
import com.flasska.findata.db.entity.ExpenseValueDb
import com.flasska.findomain.entity.Expense
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

object ExpenseMapperUtils {
    fun ExpenseTypeDb.convert(): Expense.Type {
        return Expense.Type(
            id = id,
            name = name,
            color = color
        )
    }

    fun ExpenseDb.convert(): Expense {
        return Expense(
            id = expanse.id,
            dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(expanse.dateTime), ZoneId.systemDefault()),
            value = expanse.value,
            type = type.convert()
        )
    }

    fun Expense.convert(): ExpenseDb {
        return ExpenseDb(
            expanse = ExpenseValueDb(
                id = id,
                dateTime = dateTime.toEpochSecond(ZoneOffset.UTC),
                value = value,
                typeId = type.id,
            ),
            type = type.convert()
        )
    }

    fun Expense.Type.convert(): ExpenseTypeDb {
        return ExpenseTypeDb(
            id = id,
            name = name,
            color = color
        )
    }
}

