package com.example.tarea4_consumirapiplanets.presentation.detail

import com.example.tarea4_consumirapiplanets.domain.model.Planets

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: Planets? = null,
    val error: String? = null
)