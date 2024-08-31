package com.tapascodev.dragonball.domain.model

data class CharacterModel (
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val transformations: List<TransformationModel>
)