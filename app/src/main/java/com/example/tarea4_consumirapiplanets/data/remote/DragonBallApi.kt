package com.example.tarea4_consumirapiplanets.data.remote

import com.example.tarea4_consumirapiplanets.data.remote.dtos.PlanetDto
import com.example.tarea4_consumirapiplanets.data.remote.dtos.PlanetResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallApi {
    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("name") name: String?,
        @Query("isDestroyed") isDestroyed: Boolean?
    ): Response<PlanetResponseDto>

    @GET("planets/{id}")
    suspend fun getPlanetDetail(
        @Path("id") id: Int
    ): Response<PlanetDto>
}