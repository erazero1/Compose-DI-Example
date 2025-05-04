package com.erazero1.compose_di_example.data.mapper

import com.erazero1.compose_di_example.data.model.OriginDto
import com.erazero1.compose_di_example.domain.entity.Origin

class OriginMapper: Mapper<OriginDto, Origin> {
    override fun map(input: OriginDto): Origin {
        return Origin(
            name = input.name,
            url = input.url
        )
    }
}