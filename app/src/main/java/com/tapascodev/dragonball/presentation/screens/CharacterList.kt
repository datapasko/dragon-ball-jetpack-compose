package com.tapascodev.dragonball.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.tapascodev.dragonball.domain.model.CharacterModel

@Composable
fun CharacterList (
    onClick: (CharacterModel) -> Unit,
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<CharacterModel>
) {
    LazyColumn {
        items(characters.itemCount) {
            characters[it]?.let { character -> ItemList(character, onDetailClick = onClick) }
        }
    }
}


@Composable
fun ItemList(characterModel: CharacterModel, onDetailClick: (CharacterModel) -> Unit) {
    Box(modifier = Modifier
        .padding(24.dp)
        .clip(RoundedCornerShape(24))
        .border(2.dp, Color.Blue, shape = RoundedCornerShape(0, 24, 0, 24))
        .fillMaxWidth()
        .height(250.dp)
        .background(Color.Black.copy(alpha = 0.6f))
        .clickable { onDetailClick.invoke(characterModel) },
        contentAlignment = Alignment.BottomCenter
    )
    {
        AsyncImage(
            model = characterModel.image,
            contentDescription = "character image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.6f),
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ){
            Text(text = characterModel.name, color = Color.White, fontSize = 14.sp)
            //Text(text = characterModel.ki, color = Color.White, fontSize = 14.sp)
        }
    }
}