package com.example.tarea4_consumirapiplanets.domain.usecase.Planet

import com.example.tarea4_consumirapiplanets.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(
        page: Int = 1,
        limit: Int = 10,
        name: String? = null,
        isDestroyed: Boolean? = null,
    ) = repository.getPlanets(page, limit, name, isDestroyed)
}