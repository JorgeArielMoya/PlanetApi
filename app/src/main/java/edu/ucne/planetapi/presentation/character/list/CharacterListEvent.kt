package edu.ucne.planetapi.presentation.character.list

sealed interface CharacterListEvent {
    data class UpdateName(val name: String) : CharacterListEvent
}