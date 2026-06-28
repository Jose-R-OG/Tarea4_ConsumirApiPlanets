package com.example.tarea4_consumirapiplanets.presentation.character.list

import com.example.tarea4_consumirapiplanets.domain.model.Character

data class ListCharacterUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterGender: String = "",
    val filterRace: String = ""
)