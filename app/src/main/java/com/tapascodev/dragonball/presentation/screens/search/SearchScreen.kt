package com.tapascodev.dragonball.presentation.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.presentation.screens.characters.CharacterItem
import com.tapascodev.dragonball.presentation.screens.shared.LinearProgress
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen (
    characters: Resource<List<CharacterModel>>,
    onBackClick: () -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onDetailClick: (Int) -> Unit,

    ) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = Unit) {
        delay(500)
        focusRequester.requestFocus()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        SearchBar(
            modifier = Modifier
                .focusRequester(focusRequester),
                //.onFocusChanged { isSuggestionChipsVisible = it.isFocused },
            query = searchQuery,
            onQueryChange = { onSearchQueryChange(it) },
            onSearch = {
                onSearch(searchQuery)
                keyboardController?.hide()
                focusManager.clearFocus()
            },
            active = false,
            onActiveChange = {},
            placeholder = { Text(text = "Search by name") },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search by name")
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (searchQuery.isNotEmpty()) {
                            onSearch("")
                            onSearchQueryChange("")
                        }
                        else onBackClick()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                }
            },
        ) {
            
        }


        when(characters) {
            is Resource.Failure -> {}
            Resource.Loading -> {
                if (searchQuery.isNotEmpty() || searchQuery.isNotBlank())
                    LinearProgress()
            }
            is Resource.Success -> {
                DisplayList(characters.value, onDetailClick)
            }
        }

    }
}

@Composable
fun DisplayList(characters: List<CharacterModel>, onDetailClick: (Int) -> Unit) {
    LazyVerticalGrid (
        modifier = Modifier.padding(top = 20.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = 10.dp
        )
    ){
        items(characters.size) {
            CharacterItem(
                characters[it],
                onDetailClick = { c -> onDetailClick(c.id) })
        }
    }
}