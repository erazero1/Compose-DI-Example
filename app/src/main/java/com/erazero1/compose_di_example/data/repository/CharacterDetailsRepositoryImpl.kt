package com.erazero1.compose_di_example.data.repository

import android.util.Log
import com.erazero1.compose_di_example.data.mapper.Mapper
import com.erazero1.compose_di_example.data.model.CharacterDto
import com.erazero1.compose_di_example.data.model.EpisodeDto
import com.erazero1.compose_di_example.data.remote.RickAndMortyApi
import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.Episode
import com.erazero1.compose_di_example.domain.repository.CharacterDetailsRepository

class CharacterDetailsRepositoryImpl(
    private val apiService: RickAndMortyApi,
    private val characterMapper: Mapper<CharacterDto, Character>,
    private val episodeMapper: Mapper<EpisodeDto, Episode>
): CharacterDetailsRepository {
    override suspend fun getCharacterDetails(id: Int): Result<Character> = runCatching {
        val response = apiService.getCharacterById(id)
        characterMapper.map(response)
    }

    override suspend fun getEpisodeByUrl(url: String): Result<Episode> = runCatching {
        val response = apiService.getEpisodeByUrl(url)
        Log.d("Repository", "EpisodeDto: $response")
        val mapped = episodeMapper.map(response)
        Log.d("Repository", "Mapped Episode: $mapped")
        mapped
    }
}