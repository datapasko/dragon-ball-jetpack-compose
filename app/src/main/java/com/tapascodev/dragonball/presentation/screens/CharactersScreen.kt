package com.tapascodev.dragonball.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tapascodev.dragonball.R
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.model.Resource
import com.tapascodev.dragonball.presentation.DragonBallViewModel
import com.tapascodev.dragonball.presentation.screens.shared.LinearProgress

@Composable
fun CharactersScreen (
    dragonBallViewModel: DragonBallViewModel = hiltViewModel(),
    onDetailClick: (Int) -> Unit
) {
    val characters = dragonBallViewModel.characters.collectAsState()

    when(val response = characters.value ) {
        is Resource.Failure -> {}
        Resource.Loading -> LinearProgress()
        is Resource.Success -> {
            ListCharacters(
                characters = response.value.collectAsLazyPagingItems(),
                onDetailClick = onDetailClick
            )
        }
    }
}

@Composable
fun ListCharacters(characters: LazyPagingItems<CharacterModel>, onDetailClick: (Int) -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo_dragon),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    when {

        //init Load
        characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = Color.Blue
                )
            }
        }

        //Not Data
        characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
            Text(text = "Not data")
        }

        //not network
        characters.loadState.hasError -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Network error")
            }
        }

        else -> {
            if(characters.loadState.append is LoadState.Loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp),
                        color = Color.Blue
                    )
                }
            }

            CharacterList(characters = characters, onClick = { onDetailClick(it.id) })
        }
    }

}
