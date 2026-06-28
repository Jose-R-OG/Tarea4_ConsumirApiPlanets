package com.example.tarea4_consumirapiplanets.presentation.character.detail

import com.example.tarea4_consumirapiplanets.domain.model.Character

data class DetailCharacterUiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null
)