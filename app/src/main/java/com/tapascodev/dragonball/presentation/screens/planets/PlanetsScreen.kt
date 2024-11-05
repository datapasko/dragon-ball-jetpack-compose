package com.tapascodev.dragonball.presentation.screens.planets

import androidx.compose.runtime.Composable
import com.tapascodev.dragonball.domain.model.PlanetModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.presentation.screens.shared.LinearProgress

@Composable
fun PlanetsScreen (
    onDetailClick: (Int) -> Unit,
    planets: Resource<List<PlanetModel>>
) {
    when(planets) {
        is Resource.Failure -> {}
        Resource.Loading -> LinearProgress()
        is Resource.Success -> {
            ListPlanets(
                planets = planets.value,
                onDetailClick = onDetailClick
            )
        }
    }
}