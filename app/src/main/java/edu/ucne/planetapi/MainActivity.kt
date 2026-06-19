package edu.ucne.planetapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.planetapi.presentation.navigation.AppNavHost
import edu.ucne.planetapi.ui.theme.PlanetApiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanetApiTheme {
                AppNavHost()
            }
        }
    }
}