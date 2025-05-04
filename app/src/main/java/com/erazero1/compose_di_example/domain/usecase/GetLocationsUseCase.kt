package com.erazero1.compose_di_example.domain.usecase

import com.erazero1.compose_di_example.domain.repository.LocationsRepository

class GetLocationsUseCase (
    private val repository: LocationsRepository
) {
    suspend operator fun invoke(page: Int) = repository.getLocations(page)
}