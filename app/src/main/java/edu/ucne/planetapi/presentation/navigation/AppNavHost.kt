package edu.ucne.planetapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.planetapi.presentation.character.detail.CharacterDetailScreen
import edu.ucne.planetapi.presentation.character.list.CharacterListScreen
import edu.ucne.planetapi.presentation.planet.detail.PlanetDetailScreen
import edu.ucne.planetapi.presentation.planet.list.PlanetListScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.PlanetList
    ) {
        composable<Screen.PlanetList> {
            PlanetListScreen(
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