package com.erazero1.compose_di_example.data.repository

import com.erazero1.compose_di_example.data.mapper.Mapper
import com.erazero1.compose_di_example.data.model.DetailedLocationDto
import com.erazero1.compose_di_example.data.remote.RickAndMortyApi
import com.erazero1.compose_di_example.domain.entity.DetailedLocation
import com.erazero1.compose_di_example.domain.repository.LocationsRepository

class LocationsRepositoryImpl(
    private val apiService: RickAndMortyApi,
    private val detailedLocationMapper: Mapper<DetailedLocationDto, DetailedLocation>,
): LocationsRepository {


    override suspend fun getLocations(page: Int): Result<List<DetailedLocation>> = runCatching{
        val response = apiService.getLocations(page)
        response.results.map { detailedLocationMapper.map(it) }
    }
}