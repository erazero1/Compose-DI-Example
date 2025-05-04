package com.erazero1.compose_di_example.data.mapper

import com.erazero1.compose_di_example.data.model.LocationDto
import com.erazero1.compose_di_example.domain.entity.Location

class LocationMapper: Mapper<LocationDto, Location>{
    override fun map(input: LocationDto): Location {
        return Location(
            name = input.name,
            url = input.url
        )
    }
}