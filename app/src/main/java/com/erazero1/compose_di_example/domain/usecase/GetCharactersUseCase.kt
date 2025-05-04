package com.erazero1.compose_di_example.domain.usecase

import com.erazero1.compose_di_example.domain.repository.CharactersRepository

class GetCharactersUseCase(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(page: Int) = repository.getCharacters(page)
}
