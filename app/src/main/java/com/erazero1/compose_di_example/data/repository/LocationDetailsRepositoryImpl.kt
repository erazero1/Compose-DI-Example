package com.erazero1.compose_di_example.data.repository

import com.erazero1.compose_di_example.data.mapper.Mapper
import com.erazero1.compose_di_example.data.model.CharacterDto
import com.erazero1.compose_di_example.data.model.DetailedLocationDto
import com.erazero1.compose_di_example.data.remote.RickAndMortyApi
import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.DetailedLocation
import com.erazero1.compose_di_example.domain.repository.LocationDetailsRepository

class LocationDetailsRepositoryImpl(
    private val apiService: RickAndMortyApi,
    private val detailedLocationMapper: Mapper<DetailedLocationDto, DetailedLocation>,
    private val characterMapper: Mapper<CharacterDto, Character>,
): LocationDetailsRepository {
    override suspend fun getLocationDetails(id: Int): Result<DetailedLocation> = runCatching{
        val response = apiService.getLocationById(id)
        detailedLocationMapper.map(response)
    }

    override suspend fun getResidentByUrl(url: String): Result<Character> = runCatching{
        val response = apiService.getResidentByUrl(url)
        characterMapper.map(response)
    }
}