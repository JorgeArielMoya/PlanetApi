package edu.ucne.planetapi.presentation.character.list

sealed interface CharacterListEvent {
    data class UpdateName(val name: String) : CharacterListEvent
    data class UpdateRace(val race: String) : CharacterListEvent
    data class UpdateGender(val gender: String) : CharacterListEvent
}