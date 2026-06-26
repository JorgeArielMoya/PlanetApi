package edu.ucne.planetapi.domain.character.repository

import edu.ucne.planetapi.data.remote.Resource
import kotlinx.coroutines.flow.Flow
import edu.ucne.planetapi.domain.character.model.Character

interface CharacterRepository {
    fun getCharacters(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Character>>>

    fun getCharacterDetail(id: Int): Flow<Resource<Character>>
}