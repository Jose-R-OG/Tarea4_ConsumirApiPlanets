package com.example.tarea4_consumirapiplanets.domain.usecase

import com.example.tarea4_consumirapiplanets.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(id: Int) = repository.getPlanetDetail(id)
}