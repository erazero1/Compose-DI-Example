package com.erazero1.compose_di_example.domain.usecase

import com.erazero1.compose_di_example.domain.repository.CharacterDetailsRepository

class GetEpisodeByUrlUseCase(
    private val repository: CharacterDetailsRepository
) {
    suspend operator fun invoke(url: String) = repository.getEpisodeByUrl(url)
}