package com.example.tarea4_consumirapiplanets.presentation.character.list
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarea4_consumirapiplanets.data.remote.Resource
import com.example.tarea4_consumirapiplanets.domain.usecase.character.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListCharacterUiState())
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun onEvent(event: ListCharacterEvent) {
        when (event) {
            is ListCharacterEvent.UpdateFilters -> _state.update {
                it.copy(
                    filterName = event.name,
                    filterGender = event.gender,
                    filterRace = event.race
                )
            }

            ListCharacterEvent.Search -> loadCharacters()
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val current = _state.value

            getCharactersUseCase(
                name = current.filterName.takeIf { it.isNotBlank() },
                gender = current.filterGender.takeIf { it.isNotBlank() },
                race = current.filterRace.takeIf { it.isNotBlank() }
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success -> _state.update {
                        it.copy(
                            isLoading = false,
                            characters = result.data ?: emptyList(),
                            error = null
                        )
                    }

                    is Resource.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}