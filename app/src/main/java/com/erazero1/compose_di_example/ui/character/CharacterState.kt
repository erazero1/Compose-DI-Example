package com.erazero1.compose_di_example.ui.character

import com.erazero1.compose_di_example.domain.entity.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val errorMessage: String? = null,
    var page: Int = 1,
    val characterId: Int? = null,
    val hasMorePages: Boolean = true
)