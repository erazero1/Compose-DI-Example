package com.erazero1.compose_di_example.domain.repository

import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.DetailedLocation

interface LocationDetailsRepository {
    suspend fun getLocationDetails(id: Int): Result<DetailedLocation>
    suspend fun getResidentByUrl(url: String): Result<Character>
}