package com.erazero1.compose_di_example.domain.usecase

import com.erazero1.compose_di_example.domain.repository.CharacterDetailsRepository

class GetCharacterDetailsUseCase (
    private val repository: CharacterDetailsRepository
) {
    suspend operator fun invoke(id: Int) = repository.getCharacterDetails(id)
}