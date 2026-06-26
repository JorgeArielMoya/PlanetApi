package edu.ucne.planetapi.presentation.character.detail

import edu.ucne.planetapi.domain.character.model.Character

data class CharacterDetailUiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null
)