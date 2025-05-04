package com.erazero1.compose_di_example.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erazero1.compose_di_example.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CharacterState())
    val uiState: StateFlow<CharacterState> = _uiState.asStateFlow()

    private fun loadPage(page: Int) {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = getCharactersUseCase(page)

            result.fold(
                onSuccess = { list ->
                    _uiState.update { current ->
                        val more = list.isNotEmpty()
                        current.copy(
                            isLoading = false,
                            characters = list,
                            page = page,
                            hasMorePages = more
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "Unknown error"
                        )
                    }
                }
            )
        }
    }

    fun loadNext() {
        val current = _uiState.value
        if (current.hasMorePages) loadPage(current.page + 1)
    }

    fun loadPrevious() {
        val current = _uiState.value
        if (current.page > 1) loadPage(current.page - 1)
    }

    init {
        loadPage(1)
    }
}