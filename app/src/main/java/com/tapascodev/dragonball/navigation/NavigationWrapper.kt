package com.tapascodev.dragonball.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.tapascodev.dragonball.presentation.screens.CharactersScreen
import com.tapascodev.dragonball.presentation.screens.DetailCharacterScreen
import com.tapascodev.dragonball.presentation.screens.FavoritesScreen
import com.tapascodev.dragonball.presentation.screens.PlanetsScreen

@Composable
fun NavigationWrapper (
    navHostController: NavHostController
){
    NavHost(navController = navHostController, startDestination = CharactersNav ) {

        composable<CharactersNav> {
            CharactersScreen(
                onDetailClick = { id ->
                    navHostController.navigate(DetailCharacterNav(id))
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
            PlanetsScreen()
        }

        composable<FavoritesNav> {
            FavoritesScreen()
        }

    }
}