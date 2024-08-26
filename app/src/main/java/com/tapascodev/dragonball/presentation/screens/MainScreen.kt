package com.tapascodev.dragonball.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tapascodev.dragonball.navigation.CharactersNav
import com.tapascodev.dragonball.navigation.DetailCharacterNav
import com.tapascodev.dragonball.navigation.FavoritesNav
import com.tapascodev.dragonball.navigation.NavItem
import com.tapascodev.dragonball.navigation.NavigationWrapper
import com.tapascodev.dragonball.navigation.PlanetsNav


@Composable
fun MainScreen (){

    val navItemList = listOf(
        NavItem("Characters", Icons.Default.Face),
        NavItem("Planets", Icons.Default.Place),
        NavItem("Favorites", Icons.Default.Favorite),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = "Icon"
                            )
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )

                }
            }
        }
    ) { innerPadding ->



        NavigationWrapper(navHostController = navController)

        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex,
            navController
        )

    }

}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavHostController,
) {
    when(selectedIndex) {
        0 -> navController.navigate(CharactersNav)
        1-> navController.navigate(PlanetsNav)
        2 -> navController.navigate(FavoritesNav)
    }
}
