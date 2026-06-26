package edu.ucne.planetapi.domain.character.usecase

import edu.ucne.planetapi.domain.character.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(
        page: Int = 1,
        limit: Int = 50,
        name: String? = null
    ) = repository.getCharacters(page, limit, name)
}