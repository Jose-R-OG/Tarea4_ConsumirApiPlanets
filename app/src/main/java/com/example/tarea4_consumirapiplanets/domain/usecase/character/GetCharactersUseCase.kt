package com.example.tarea4_consumirapiplanets.domain.usecase.character

import com.example.tarea4_consumirapiplanets.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repo: CharacterRepository) {
    operator fun invoke(page: Int = 1, limit: Int = 10, name: String? = null, gender: String? = null, race: String? = null) =
        repo.getCharacters(page, limit, name, gender, race)
}