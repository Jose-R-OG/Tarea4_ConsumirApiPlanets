package com.example.tarea4_consumirapiplanets.domain.repository

import com.example.tarea4_consumirapiplanets.data.remote.Resource
import com.example.tarea4_consumirapiplanets.data.remote.dtos.PlanetDto

interface PlanetRepository {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Resource<List<PlanetDto>>

    suspend fun getPlanetDetail(id: Int): Resource<PlanetDto>
}