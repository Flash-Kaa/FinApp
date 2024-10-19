package com.flasska.findomain.usecase

import com.flasska.findomain.repository.Repository

class GetAllUseCase<T>(
    private val repository: Repository<T>
) {
    suspend operator fun invoke() = repository.getAll()
}