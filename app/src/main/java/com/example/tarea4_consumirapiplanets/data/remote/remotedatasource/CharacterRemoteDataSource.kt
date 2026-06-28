package com.example.tarea4_consumirapiplanets.data.remote.remotedatasource

import com.example.tarea4_consumirapiplanets.data.remote.DragonBallApi
import com.example.tarea4_consumirapiplanets.data.remote.dtos.CharacterDto
import com.example.tarea4_consumirapiplanets.data.remote.dtos.CharacterResponseDto
import retrofit2.Response
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(private val api: DragonBallApi) {
    suspend fun getCharacters(page: Int, limit: Int, name: String?, gender: String?, race: String?): Result<CharacterResponseDto> {
        return try {
            val response = if (name != null || gender != null || race != null) {
                val searchResponse = api.searchCharacters(name, gender, race)
                if (searchResponse.isSuccessful) {
                    Response.success(CharacterResponseDto(searchResponse.body() ?: emptyList()))
                } else {
                    Response.error(searchResponse.code(), searchResponse.errorBody()!!)
                }
            } else {
                api.getCharacters(page, limit)
            }

            if (response.isSuccessful) Result.success(response.body()!!) else Result.failure(Exception("Error de red"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCharacterDetail(id: Int): Result<CharacterDto> {
        return try {
            val response = api.getCharacterDetail(id)
            if (response.isSuccessful) Result.success(response.body()!!) else Result.failure(Exception("Error de red"))
        } catch (e: Exception) { Result.failure(e) }
    }
}