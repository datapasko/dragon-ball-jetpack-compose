package com.tapascodev.dragonball.data.repository

import com.tapascodev.dragonball.data.network.PlanetApi
import com.tapascodev.dragonball.domain.model.PlanetModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.repository.PlanetsRepository
import com.tapascodev.dragonball.data.network.SafeApiCall
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val api: PlanetApi
): PlanetsRepository, SafeApiCall {

    override suspend fun getPlanets(): Resource<List<PlanetModel>> {
        val response = api.getPlanets()
        return safeApiCall { response.items.map { it.toDomain() } }
    }

    override suspend fun getPlanet(id: Int): PlanetModel {
        return api.getPlanet(id).toDomain()
    }
}