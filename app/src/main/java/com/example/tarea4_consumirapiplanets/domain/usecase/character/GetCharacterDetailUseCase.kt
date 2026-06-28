package com.example.tarea4_consumirapiplanets.domain.usecase.character

import com.example.tarea4_consumirapiplanets.domain.repository.CharacterRepository
import jakarta.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(private val repo: CharacterRepository) {
    operator fun invoke(id: Int) = repo.getCharacterDetail(id)
}