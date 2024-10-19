package com.flasska.findomain.usecase

import com.flasska.findomain.repository.Repository

class DeleteUseCase<T>(
    private val repository: Repository<T>
) {
    suspend operator fun invoke(value: T) = repository.delete(value)
}