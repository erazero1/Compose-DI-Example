package com.erazero1.compose_di_example.domain.repository

import com.erazero1.compose_di_example.domain.entity.Character

interface CharactersRepository {
    suspend fun getCharacters(page: Int): Result<List<Character>>
}