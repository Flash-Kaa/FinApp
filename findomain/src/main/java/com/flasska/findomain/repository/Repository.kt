package com.flasska.findomain.repository

import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    suspend fun getAll(): Flow<List<T>>

    suspend fun add(value: T)

    suspend fun delete(value: T)
}