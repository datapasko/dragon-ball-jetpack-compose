package com.tapascodev.dragonball.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.tapascodev.dragonball.presentation.screens.FavoritesScreen
import com.tapascodev.dragonball.presentation.screens.planets.PlanetsScreen
import com.tapascodev.dragonball.presentation.screens.characters.CharacterViewModel
import com.tapascodev.dragonball.presentation.screens.characters.CharactersScreen
import com.tapascodev.dragonball.presentation.screens.characters.DetailCharacterScreen
import com.tapascodev.dragonball.presentation.screens.planets.DetailPlanetScreen
import com.tapascodev.dragonball.presentation.screens.planets.PlanetViewModel
import com.tapascodev.dragonball.presentation.screens.search.SearchScreen
import com.tapascodev.dragonball.presentation.screens.search.SearchViewModel

@Composable
fun NavigationWrapper (
    navHostController: NavHostController,
    modifier: Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
){
    NavHost(navController = navHostController, startDestination = CharactersNav, modifier = modifier ) {

        composable<CharactersNav> {
            val characterViewModel: CharacterViewModel = hiltViewModel()
            val characters = characterViewModel.characters.collectAsState()
            CharactersScreen(
                characters = characters,
                onDetailClick = { id ->
                    navHostController.navigate(DetailCharacterNav(id))
                },
                onNavigationSearch = {
                    navHostController.navigate(SearchNav)
                }
            )
        }

        composable<DetailCharacterNav> { backStackEntry ->
            val detail = backStackEntry.toRoute<DetailCharacterNav>()
            DetailCharacterScreen(
                id = detail.id,
                onBack = { navHostController.popBackStack() }
            )

        }

        composable<PlanetsNav> {
            val planetViewModel: PlanetViewModel = hiltViewModel()

            LaunchedEffect(Unit) {
                planetViewModel.getPlanets()
            }

            val planets = planetViewModel.planets.collectAsState()
            PlanetsScreen(
                planets = planets.value,
                onDetailClick = { id ->
                    navHostController.navigate(DetailPlanetNav(id))
                },
            )
        }

        composable<DetailPlanetNav> {backStackEntry ->
            val detail = backStackEntry.toRoute<DetailPlanetNav>()

            val planetViewModel: PlanetViewModel = hiltViewModel()

            LaunchedEffect(Unit) {
                planetViewModel.getPlanet(detail.id)
            }

            val planet = planetViewModel.planet.collectAsState()

            DetailPlanetScreen(
                planetModel = planet.value,
                onBack = { navHostController.popBackStack() },
            )


        }

        composable<FavoritesNav> {
            FavoritesScreen()
        }

        composable<SearchNav> {

            val searchViewModel: SearchViewModel = hiltViewModel()
            val characters = searchViewModel.characters.collectAsState()

            SearchScreen(
                characters = characters.value,
                onBackClick = { navHostController.popBackStack() },
                searchQuery = searchQuery,
                onSearchQueryChange = { onSearchQueryChange(it) },
                onSearch = { searchViewModel.searchCharacter(it) },
                onDetailClick = { id ->
                    navHostController.navigate(DetailCharacterNav(id))
                },
            )
        }

    }
}