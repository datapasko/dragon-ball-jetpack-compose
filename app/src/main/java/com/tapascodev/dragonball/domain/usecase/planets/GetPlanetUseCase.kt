package com.tapascodev.dragonball.domain.usecase.planets

import com.tapascodev.dragonball.domain.model.PlanetModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.repository.PlanetsRepository
import com.tapascodev.dragonball.data.network.SafeApiCall
import javax.inject.Inject

class GetPlanetUseCase @Inject constructor(
    val repository: PlanetsRepository
): SafeApiCall {
    suspend operator fun invoke(id: Int): Resource<PlanetModel> = safeApiCall { repository.getPlanet(id) }
}