package com.erazero1.compose_di_example.ui.location

import com.erazero1.compose_di_example.domain.entity.DetailedLocation

data class LocationState(
    val isLoading: Boolean = false,
    val locations: List<DetailedLocation> = emptyList(),
    val errorMessage: String? = null,
    val page: Int = 1,
    val hasMorePages: Boolean = true,
    val selectedLocation: DetailedLocation? = null
)