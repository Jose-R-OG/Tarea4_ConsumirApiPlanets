package com.example.tarea4_consumirapiplanets.data.repository

import com.example.tarea4_consumirapiplanets.data.remote.Resource
import com.example.tarea4_consumirapiplanets.data.remote.remotedatasource.CharacterRemoteDataSource
import com.example.tarea4_consumirapiplanets.domain.model.Character
import com.example.tarea4_consumirapiplanets.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override fun getCharacters(page: Int, limit: Int, name: String?, gender: String?, race: String?): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getCharacters(page, limit, name, gender, race)
            .onSuccess { emit(Resource.Success(it.items.map { dto -> dto.toDomain() })) }
            .onFailure { emit(Resource.Error(it.message ?: "Error")) }
    }

    override fun getCharacterDetail(id: Int): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getCharacterDetail(id)
            .onSuccess { emit(Resource.Success(it.toDomain())) }
            .onFailure { emit(Resource.Error(it.message ?: "Error")) }
    }
}