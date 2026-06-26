package edu.ucne.planetapi.presentation.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.planetapi.presentation.character.detail.CharacterDetailScreen
import edu.ucne.planetapi.presentation.character.list.CharacterListScreen
import edu.ucne.planetapi.presentation.planet.detail.PlanetDetailScreen
import edu.ucne.planetapi.presentation.planet.list.PlanetListScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    DrawerMenu(
        drawerState = drawerState,
        navHostController = navController
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.PlanetList
        ) {
            composable<Screen.PlanetList> {
                PlanetListScreen(
                    onDrawer = { scope.launch { drawerState.open() } },
                    onPlanetClick = { id ->
                        navController.navigate(Screen.PlanetDetail(id))
                    }
                )
            }
            composable<Screen.PlanetDetail> {
                PlanetDetailScreen(
                    onBack = { navController.navigateUp() }
                )
            }
            composable<Screen.CharacterList> {
                CharacterListScreen(
                    onDrawer = { scope.launch { drawerState.open() } },
                    onCharacterClick = { id ->
                        navController.navigate(Screen.CharacterDetail(id))
                    }
                )
            }
            composable<Screen.CharacterDetail> {
                CharacterDetailScreen(
                    onBack = { navController.navigateUp() }
                )
            }
        }
    }
}