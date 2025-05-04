package com.erazero1.compose_di_example.data.mapper

import com.erazero1.compose_di_example.data.model.EpisodeDto
import com.erazero1.compose_di_example.domain.entity.Episode

class EpisodeMapper : Mapper<EpisodeDto, Episode> {
    override fun map(input: EpisodeDto): Episode {
        return Episode(
            name = input.name,
            episode = input.episode,
            characters = input.characters,
            airDate = input.airDate,
            url = input.url
        )
    }
}