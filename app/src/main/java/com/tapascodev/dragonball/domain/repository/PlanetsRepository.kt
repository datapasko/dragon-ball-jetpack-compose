package com.tapascodev.dragonball.domain.repository

import com.tapascodev.dragonball.domain.model.PlanetModel
import com.tapascodev.dragonball.data.network.Resource
import kotlinx.coroutines.flow.Flow

interface PlanetsRepository {
    suspend fun getPlanets(): Resource<List<PlanetModel>>
    suspend fun getPlanet(id: Int): PlanetModel
}