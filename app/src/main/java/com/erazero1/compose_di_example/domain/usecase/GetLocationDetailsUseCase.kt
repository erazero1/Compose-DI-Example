package com.erazero1.compose_di_example.domain.usecase

import com.erazero1.compose_di_example.domain.repository.LocationDetailsRepository

class GetLocationDetailsUseCase (
    private val repository: LocationDetailsRepository
) {
    suspend operator fun invoke(id: Int) = repository.getLocationDetails(id)
}