package com.erazero1.compose_di_example.domain.entity

data class DetailedLocation(
    val dimension: String = "Unknown",
    val name: String = "Unknown",
    val residents: List<String> = emptyList(),
    val type: String = "Unknown",
    val url: String = "",
    val id: Int = -1
)
