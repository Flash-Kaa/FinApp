package com.flasska.findomain.entity

import java.time.LocalDate

data class Expense(
    val id: Int = 0,
    val dateTime: LocalDate,
    val value: Float,
    val type: Type
) {
    data class Type(
        val id: Int = 0,
        val name: String,
        val color: Int
    )
}
