package com.erazero1.compose_di_example.data.mapper

import com.erazero1.compose_di_example.data.model.CharacterDto
import com.erazero1.compose_di_example.data.model.LocationDto
import com.erazero1.compose_di_example.data.model.OriginDto
import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.Location
import com.erazero1.compose_di_example.domain.entity.Origin

class CharacterMapper(
    private val locationMapper: Mapper<LocationDto, Location>,
    private val originMapper: Mapper<OriginDto, Origin>
): Mapper<CharacterDto, Character>{
    override fun map(input: CharacterDto): Character {
        return Character(
            created = input.created,
            episode = input.episode,
            gender = input.gender,
            id = input.id,
            image = input.image,
            name = input.name,
            species = input.species,
            status = input.status,
            type = input.type,
            url = input.url,
            location = locationMapper.map(input.location),
            origin = originMapper.map(input.origin)
        )
    }
}