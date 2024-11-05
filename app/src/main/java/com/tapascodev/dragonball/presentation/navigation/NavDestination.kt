package com.tapascodev.dragonball.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object CharactersNav

@Serializable
data class DetailCharacterNav(
    val id: Int
)

@Serializable
object PlanetsNav

@Serializable
data class DetailPlanetNav( val id: Int)

@Serializable
object FavoritesNav

@Serializable
object SearchNav