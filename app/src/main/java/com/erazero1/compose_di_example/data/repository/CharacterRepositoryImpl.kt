package com.erazero1.compose_di_example.data.repository

import com.erazero1.compose_di_example.data.mapper.Mapper
import com.erazero1.compose_di_example.data.model.CharacterDto
import com.erazero1.compose_di_example.data.remote.RickAndMortyApi
import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.repository.CharactersRepository

class CharacterRepositoryImpl(
    private val apiService: RickAndMortyApi,
    private val mapper: Mapper<CharacterDto, Character>
) : CharactersRepository {

    override suspend fun getCharacters(page: Int): Result<List<Character>> = runCatching {
        val response = apiService.getCharacters(page)
        response.results.map { mapper.map(it) }
    }

}