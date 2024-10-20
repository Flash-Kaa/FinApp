package com.flasska.finapp.ui.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

object DateUtils {
    private val format = SimpleDateFormat("dd.MM.yyyy")

    fun LocalDate.getDateInFormat(): String {
        val date = Date.from(atStartOfDay().toInstant(ZoneOffset.UTC))
        return format.format(date)
    }

    fun LocalDateTime.getDateInFormat(): String {
        val date = Date.from(toInstant(ZoneOffset.UTC))
        return format.format(date)
    }
}