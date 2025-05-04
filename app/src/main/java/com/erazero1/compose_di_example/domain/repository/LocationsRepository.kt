package com.erazero1.compose_di_example.domain.repository

import com.erazero1.compose_di_example.domain.entity.DetailedLocation

interface LocationsRepository {
    suspend fun getLocations(page: Int): Result<List<DetailedLocation>>
}