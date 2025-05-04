package com.erazero1.compose_di_example.data.model

data class DetailedLocationDto(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)