package com.flasska.findomain.usecase

import com.flasska.findomain.repository.Repository

class AddUseCase<T>(
    private val repository: Repository<T>
) {
    suspend operator fun invoke(value: T) = repository.add(value)
}