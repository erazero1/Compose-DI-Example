package com.erazero1.compose_di_example.domain.repository

import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.Episode

interface CharacterDetailsRepository {
    suspend fun getCharacterDetails(id: Int): Result<Character>
    suspend fun getEpisodeByUrl(url: String): Result<Episode>
}