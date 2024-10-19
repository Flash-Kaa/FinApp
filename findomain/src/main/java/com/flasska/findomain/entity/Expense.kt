package com.flasska.findomain.entity

import java.time.LocalDateTime

data class Expense(
    val id: Int,
    val dateTime: LocalDateTime,
    val value: Float,
    val type: Type
) {
    data class Type(
        val id: Int,
        val name: String,
        val color: Int
    )
}
