package com.erazero1.compose_di_example.domain.usecase

import com.erazero1.compose_di_example.domain.repository.LocationDetailsRepository

class GetResidentByUrlUseCase(
    private val repository: LocationDetailsRepository
) {
    suspend operator fun invoke(url: String) = repository.getResidentByUrl(url)
}