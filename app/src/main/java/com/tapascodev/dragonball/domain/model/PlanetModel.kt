package com.tapascodev.dragonball.domain.model

data class PlanetModel (
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String,
    val characters: List<CharacterModel> = emptyList()
)