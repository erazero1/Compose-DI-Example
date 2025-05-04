package com.erazero1.compose_di_example.domain.entity

data class Episode(
    val name: String = "Unknown",
    val episode: String = "Unknown",
    val characters: List<String> = emptyList(),
    val airDate: String = "Unknown",
    val url: String = "",
)