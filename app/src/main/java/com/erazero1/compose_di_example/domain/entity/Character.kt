package com.erazero1.compose_di_example.domain.entity

data class Character(
    val created: String = "Unknown",
    val episode: List<String> = emptyList(),
    val gender: String = "Unknown",
    val id: Int = -1,
    val image: String = "https://rickandmortyapi.com/api/character/avatar/19.jpeg",
    val name: String = "Unknown",
    val species: String = "Unknown",
    val status: String = "Unknown",
    val type: String = "Unknown",
    val url: String = "",
    val location: Location = Location(),
    val origin: Origin = Origin()
)

data class Location(
    val name: String = "Unknown",
    val url: String = ""
)

data class Origin(
    val name: String = "Unknown",
    val url: String = ""
)


