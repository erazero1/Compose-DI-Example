package com.erazero1.compose_di_example.data.mapper

import com.erazero1.compose_di_example.data.model.DetailedLocationDto
import com.erazero1.compose_di_example.domain.entity.DetailedLocation

class DetailedLocationMapper: Mapper<DetailedLocationDto, DetailedLocation> {
    override fun map(input: DetailedLocationDto): DetailedLocation {
        return DetailedLocation(
            dimension = input.dimension,
            name = input.name,
            residents = input.residents,
            type = input.type,
            url = input.url,
            id = input.id
        )
    }

}