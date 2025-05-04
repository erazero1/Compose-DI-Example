package com.erazero1.compose_di_example.ui.character_details

import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.Episode

data class CharacterDetailsState(
    val isLoading: Boolean = false,
    val character: Character = Character(),
    val episode: Episode = Episode(),
    val errorMessage: String? = null
)