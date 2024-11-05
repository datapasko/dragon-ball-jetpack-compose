package com.tapascodev.dragonball.domain.usecase.planets

import com.tapascodev.dragonball.domain.model.PlanetModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.repository.PlanetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllPlanetsUseCase @Inject constructor(
    val repository: PlanetsRepository
) {
    suspend operator fun invoke(): Resource<List<PlanetModel>> = repository.getPlanets()
}