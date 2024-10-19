package com.flasska.findomain.repository

interface Repository<T> {
    suspend fun getAll(): List<T>

    suspend fun add(value: T)

    suspend fun delete(value: T)
}