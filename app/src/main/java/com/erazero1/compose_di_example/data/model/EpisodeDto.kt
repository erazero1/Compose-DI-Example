package com.erazero1.compose_di_example.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("air_date") val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)