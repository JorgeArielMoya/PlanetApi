package edu.ucne.planetapi.domain.character.usecase

import edu.ucne.planetapi.domain.character.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int) = repository.getCharacterDetail(id)
}