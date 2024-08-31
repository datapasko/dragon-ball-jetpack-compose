package com.tapascodev.dragonball.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                LinearProgress()
            }
            SetContent(characters = characters, onDetailClick = onDetailClick)
        }
    }
}

@Composable
fun SetContent(
    characters: LazyPagingItems<CharacterModel>,
    onDetailClick: (Int) -> Unit
) {

    var search by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

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

        TextField(
            value = search,
            onValueChange = { search = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clip(MaterialTheme.shapes.large),
            placeholder = { Text(text = "Search") },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        CharacterList(onClick = { onDetailClick(it.id) }, characters = characters)
    }
}
