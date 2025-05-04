package com.erazero1.compose_di_example.data.remote

import com.erazero1.compose_di_example.data.model.CharacterDto
import com.erazero1.compose_di_example.data.model.DetailedLocationDto
import com.erazero1.compose_di_example.data.model.EpisodeDto
import com.erazero1.compose_di_example.data.model.PaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyApi {
    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDto

    @GET
    suspend fun getEpisodeByUrl(
        @Url url: String
    ): EpisodeDto

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): PaginatedResponse<CharacterDto>

    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") id: Int
    ): DetailedLocationDto

    @GET
    suspend fun getResidentByUrl(
        @Url url: String
    ): CharacterDto

    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int
    ): PaginatedResponse<DetailedLocationDto>
}