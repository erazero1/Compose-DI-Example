package com.erazero1.compose_di_example.ui.location_details

import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.DetailedLocation

data class LocationDetailsState(
    val isLoading: Boolean = false,
    val location: DetailedLocation = DetailedLocation(),
    val residents: List<Character> = emptyList(),
    val errorMessage: String? = null
)