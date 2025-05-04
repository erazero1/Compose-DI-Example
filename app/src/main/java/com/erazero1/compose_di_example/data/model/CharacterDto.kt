package com.erazero1.compose_di_example.data.model

import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.Location
import com.erazero1.compose_di_example.domain.entity.Origin

data class CharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDto,
    val name: String,
    val origin: OriginDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun CharacterDto.toDomain(): Character = Character(
    created = created,
    episode = episode,
    gender = gender,
    id = id,
    image = image,
    name = name,
    species = species,
    status = status,
    type = type,
    url = url,
    location = location.toDomain(),
    origin = origin.toDomain()
)


data class LocationDto(
    val name: String,
    val url: String
)

fun LocationDto.toDomain(): Location = Location(
    name = name,
    url = url
)

data class OriginDto(
    val name: String,
    val url: String
)

fun OriginDto.toDomain(): Origin = Origin(
    name = name,
    url = url
)
