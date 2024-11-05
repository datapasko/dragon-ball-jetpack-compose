package com.tapascodev.dragonball.presentation.screens.planets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tapascodev.dragonball.domain.model.PlanetModel

@Composable
fun ItemPlanet(
    planetModel: PlanetModel,
    onDetailClick: (Int) -> Unit
) {

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onDetailClick.invoke(planetModel.id) },
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
        ),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),

    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = planetModel.image,
                contentDescription = "planet image",
                modifier = Modifier
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                    .size(150.dp)
                    .drawWithContent {
                        val colors = listOf(
                            Color.Black,
                            Color.Transparent
                        )
                        drawContent()
                        drawRect(
                            brush = Brush.horizontalGradient(colors),
                            blendMode = BlendMode.DstIn
                        )
                    },
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.wrapContentWidth(Alignment.End),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleLarge,
                color = Color.LightGray,
                text = planetModel.name,

            )
        }
    }

}