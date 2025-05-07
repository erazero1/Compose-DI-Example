package com.erazero1.compose_di_example.ui.character_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.Episode
import com.erazero1.compose_di_example.domain.usecase.GetCharacterDetailsUseCase
import com.erazero1.compose_di_example.domain.usecase.GetEpisodeByUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getEpisodeByUrlUseCase: GetEpisodeByUrlUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = MutableStateFlow(CharacterDetailsState())
    val uiState: StateFlow<CharacterDetailsState> = _uiState.asStateFlow()
    val id: Int = savedStateHandle["id"]?: -1


    private fun getCharacterDetails() {
        viewModelScope.launch {

            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }

            val result = getCharacterDetailsUseCase(id = id)

            if (result.isSuccess) {
                val url = result.getOrNull()?.episode?.firstOrNull()
                if (url != null){
                    val episode = getEpisodeByUrlUseCase(url = url)
                    Log.d("CharacterDetailsViewModel", episode.getOrNull().toString())
                    if (episode.isSuccess) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                episode = episode.getOrNull() ?: Episode(),
                                character = result.getOrNull() ?: Character()
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                character = result.getOrNull() ?: Character()
                            )
                        }
                    }
                }else {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            character = result.getOrNull() ?: Character(),
                            errorMessage = "No episode URL found"
                        )
                    }
                }


            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.localizedMessage ?: "Unknown error"
                    )
                }
            }
        }
    }

    init {
        getCharacterDetails()
    }
}