package com.erazero1.compose_di_example.ui.location_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erazero1.compose_di_example.domain.usecase.GetLocationDetailsUseCase
import com.erazero1.compose_di_example.domain.usecase.GetResidentByUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase,
    private val getResidentByUrlUseCase: GetResidentByUrlUseCase,
    savedStateHandle: SavedStateHandle
) :ViewModel() {
    private val _uiState = MutableStateFlow(LocationDetailsState())
    val uiState: StateFlow<LocationDetailsState> = _uiState.asStateFlow()
    val id: Int = savedStateHandle["id"]?:-1

    init {
        loadLocationDetails()
    }

    private fun loadLocationDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val locResult = getLocationDetailsUseCase(id)
            if (locResult.isSuccess) {
                val location = locResult.getOrNull()!!
                _uiState.update { it.copy(location = location) }

                val residentsDeferred = location.residents.map { url ->
                    async { getResidentByUrlUseCase(url) }
                }
                val residentsList = residentsDeferred.mapNotNull { deferred ->
                    val res = deferred.await()
                    res.getOrNull()
                }
                _uiState.update {
                    it.copy(isLoading = false, residents = residentsList)
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = locResult.exceptionOrNull()?.localizedMessage ?: "Unknown error"
                    )
                }
            }
        }
    }
}