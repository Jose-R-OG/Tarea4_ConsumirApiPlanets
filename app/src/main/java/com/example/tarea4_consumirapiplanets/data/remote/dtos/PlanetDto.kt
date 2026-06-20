package com.example.tarea4_consumirapiplanets.data.remote.dtos

import com.example.tarea4_consumirapiplanets.domain.model.Planets


data class PlanetResponseDto(
    val items: List<PlanetDto>
)

data class PlanetDto(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String,
) {
    fun toDomain() = Planets(
        id = id,
        name = name,
        isDestroyed = isDestroyed,
        description = description,
        image = image,
    )
}