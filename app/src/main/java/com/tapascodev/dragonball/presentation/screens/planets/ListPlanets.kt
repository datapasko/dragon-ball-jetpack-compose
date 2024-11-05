package com.tapascodev.dragonball.presentation.screens.planets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tapascodev.dragonball.R
import com.tapascodev.dragonball.domain.model.PlanetModel

@Composable
fun ListPlanets(
    planets: List<PlanetModel>,
    onDetailClick: (Int) -> Unit
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painterResource(id = R.drawable.banner_dragon_ball),
            "banner",
            modifier = Modifier
                .padding(10.dp)
                .height(60.dp),
            contentScale = ContentScale.Fit
        )

        LazyColumn (
            modifier = Modifier.padding(top = 20.dp),
            contentPadding = PaddingValues(
                top = 10.dp
            )
        ) {
            items(planets.size){
                ItemPlanet(planetModel = planets[it], onDetailClick)
            }
        }
    }
}