package com.tapascodev.dragonball

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tapascodev.dragonball.presentation.navigation.CharactersNav
import com.tapascodev.dragonball.presentation.navigation.FavoritesNav
import com.tapascodev.dragonball.presentation.navigation.NavItem
import com.tapascodev.dragonball.presentation.navigation.NavigationWrapper
import com.tapascodev.dragonball.presentation.navigation.PlanetsNav
import com.tapascodev.dragonball.presentation.screens.MainScreen
import com.tapascodev.dragonball.ui.theme.DragonBallTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            DragonBallTheme {
                val navItemList = listOf(
                    NavItem("Characters", Icons.Default.Face, CharactersNav),
                    NavItem("Planets", Icons.Default.Place, PlanetsNav),
                    NavItem("Favorites", Icons.Default.Favorite, FavoritesNav),
                )

                val navController = rememberNavController()
                var searchQuery by rememberSaveable { mutableStateOf("") }

                Surface {
                    Scaffold(
                        containerColor = MaterialTheme.colorScheme.background,
                        bottomBar = {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            NavigationBar {
                                navItemList.forEach { navItem ->
                                    NavigationBarItem(
                                        selected = currentDestination?.hasRoute(navItem.route::class) == true,
                                        onClick = {
                                            navController.navigate(navItem.route) {
                                                // Pop up to the start destination of the graph to
                                                // avoid building up a large stack of destinations
                                                // on the back stack as users select items
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                // Avoid multiple copies of the same destination when
                                                // reselecting the same item
                                                launchSingleTop = true
                                                // Restore state when reselecting a previously selected item
                                                restoreState = true
                                            }
                                        },
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

                        NavigationWrapper(
                            navHostController = navController,
                            modifier = Modifier.padding(innerPadding),
                            searchQuery = searchQuery,
                            onSearchQueryChange = { searchQuery = it }
                        )
                    }
                }
            }
        }
    }
}