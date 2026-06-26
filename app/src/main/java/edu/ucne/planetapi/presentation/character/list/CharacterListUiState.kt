package edu.ucne.planetapi.presentation.character.list

import edu.ucne.planetapi.domain.character.model.Character

data class CharacterListUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val charactersFiltrados: List<Character> = emptyList(),
    val error: String? = null,
    val filterName: String = ""
)