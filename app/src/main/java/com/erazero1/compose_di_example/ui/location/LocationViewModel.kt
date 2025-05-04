package com.erazero1.compose_di_example.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erazero1.compose_di_example.domain.usecase.GetLocationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationViewModel(
    private val getLocationsUseCase: GetLocationsUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(LocationState())
    val uiState: StateFlow<LocationState> = _uiState.asStateFlow()

    init {
        loadLocationPage(1)
    }

    private fun loadLocationPage(page: Int) {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = getLocationsUseCase(page)

            result.fold(
                onSuccess = { list ->
                    val more = list.isNotEmpty()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            locations = list,
                            page = page,
                            hasMorePages = more
                        )
                    }
                },
                onFailure = {err ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = err.localizedMessage ?: "Unknown error"
                        )
                    }
                }
            )
        }
    }

    fun loadNextPage() {
        val state = _uiState.value
        if (state.hasMorePages) loadLocationPage(state.page + 1)
    }

    fun loadPreviousPage() {
        val state = _uiState.value
        if (state.page > 1) loadLocationPage(state.page - 1)
    }
}