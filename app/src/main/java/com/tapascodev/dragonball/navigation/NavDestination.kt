package com.tapascodev.dragonball.navigation

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
object FavoritesNav