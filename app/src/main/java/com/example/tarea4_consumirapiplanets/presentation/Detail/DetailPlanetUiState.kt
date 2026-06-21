package com.example.tarea4_consumirapiplanets.presentation.detail

import com.example.tarea4_consumirapiplanets.data.remote.dtos.PlanetDto

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: PlanetDto? = null,
    val error: String? = null
)