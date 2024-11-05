package com.tapascodev.dragonball.presentation.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tapascodev.dragonball.domain.model.CharacterModel

@Composable
fun CharacterItem(characterModel: CharacterModel, onDetailClick: (CharacterModel) -> Unit) {

    /* Card (
         modifier = Modifier
             .padding(16.dp)
             .clickable { onDetailClick.invoke(characterModel) },
         colors = CardDefaults.cardColors(
             containerColor = MaterialTheme.colorScheme.inverseSurface,
         ),
         shape = MaterialTheme.shapes.large,
         elevation = CardDefaults.cardElevation(
             defaultElevation = 5.dp
         )

     ) */

    Box(
        modifier = Modifier
            .padding(16.dp)
            .width(170.dp)
            .height(170.dp)
            .clickable { onDetailClick.invoke(characterModel) }
            .background(
                MaterialTheme.colorScheme.inverseSurface,
                shape = MaterialTheme.shapes.large
            ),

        ){
        Column {

            AsyncImage(
                model = characterModel.image,
                contentDescription = "character image",
                modifier = Modifier
                    .graphicsLayer {
                        scaleY = 1.2f
                        scaleX = 1.2f
                        this.transformOrigin = TransformOrigin(1f, 1f)
                    }
                    .size(130.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )


            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.DarkGray.copy(alpha = 0f),
                                Color.DarkGray.copy(alpha = 0.1f),
                                Color.DarkGray.copy(alpha = 0.2f),
                                Color.DarkGray.copy(alpha = 0.4f),
                                Color.DarkGray.copy(alpha = 0.6f),
                            )
                        ),
                        shape = MaterialTheme.shapes.large
                    ),
                contentAlignment = Alignment.Center
            ){
                Text(text = characterModel.name, style = MaterialTheme.typography.titleMedium, color = Color.LightGray)
            }

        }


    }

}