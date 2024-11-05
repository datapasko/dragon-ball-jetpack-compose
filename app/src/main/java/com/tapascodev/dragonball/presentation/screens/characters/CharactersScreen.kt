package com.tapascodev.dragonball.presentation.screens.characters

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tapascodev.dragonball.R
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.presentation.screens.shared.LinearProgress
import kotlinx.coroutines.flow.Flow

@Composable
fun CharactersScreen (
    onDetailClick: (Int) -> Unit,
    characters: State<Resource<Flow<PagingData<CharacterModel>>>>,
    onNavigationSearch: () -> Unit
) {

    when(val response = characters.value ) {
        is Resource.Failure -> {}
        Resource.Loading -> LinearProgress()
        is Resource.Success -> {
            ListCharacters(
                characters = response.value.collectAsLazyPagingItems(),
                onDetailClick = onDetailClick,
                onNavigationSearch = onNavigationSearch
            )
        }
    }

}

@Composable
fun ListCharacters(
    characters: LazyPagingItems<CharacterModel>,
    onDetailClick: (Int) -> Unit,
    onNavigationSearch: () -> Unit
) {


    val context = LocalContext.current
    LaunchedEffect(key1 = characters.loadState) {
        if(characters.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (characters.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        when {

            //init Load
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            //Not Data
            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Not data"
                )
            }

            else -> {
                DisplayList(characters = characters, onDetailClick = onDetailClick, onNavigationSearch = onNavigationSearch)
            }
        }


    }

}

@Composable
fun DisplayList(
    characters: LazyPagingItems<CharacterModel>,
    onDetailClick: (Int) -> Unit,
    onNavigationSearch: () -> Unit
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

        TextField(
            enabled = false,
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clip(MaterialTheme.shapes.large)
                .clickable { onNavigationSearch() },
            placeholder = { Text(text = "Search by name") },
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


        LazyVerticalGrid (
            modifier = Modifier.padding(top = 20.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                top = 10.dp
            )
        ){
            items(characters.itemCount) {
                characters[it]?.let { character -> CharacterItem(character, onDetailClick = { c -> onDetailClick(c.id)}) }
            }

            item {
                if (characters.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
