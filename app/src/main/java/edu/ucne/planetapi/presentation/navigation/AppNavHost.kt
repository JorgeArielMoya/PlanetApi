package edu.ucne.planetapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.planetapi.presentation.planet.detail.PlanetDetailScreen
import edu.ucne.planetapi.presentation.planet.list.PlanetListScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ListScreen
    ) {
        composable<Screen.ListScreen> {
            PlanetListScreen(
                onPlanetClick = { id ->
                    navController.navigate(Screen.Detail(id))
                }
            )
        }
        composable<Screen.Detail> {
            PlanetDetailScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}