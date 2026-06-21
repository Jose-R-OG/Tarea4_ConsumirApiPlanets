package com.example.tarea4_consumirapiplanets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tarea4_consumirapiplanets.presentation.detail.DetailPlanetScreen
import com.example.tarea4_consumirapiplanets.presentation.detail.DetailPlanetViewModel
import com.example.tarea4_consumirapiplanets.presentation.list.ListPLanetScreen
import com.example.tarea4_consumirapiplanets.presentation.list.ListPlanetViewModel
import com.example.tarea4_consumirapiplanets.presentation.navigation.DetailScreen
import com.example.tarea4_consumirapiplanets.presentation.navigation.ListScreen
import com.example.tarea4_consumirapiplanets.ui.theme.Tarea4_ConsumirApiPlanetsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tarea4_ConsumirApiPlanetsTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ListScreen
                ) {
                    composable<ListScreen> {
                        val viewModel = hiltViewModel<ListPlanetViewModel>()
                        ListPLanetScreen(
                            viewModel = viewModel,
                            onPlanetClick = { planetId ->
                                navController.navigate(DetailScreen(id = planetId))
                            }
                        )
                    }

                    composable<DetailScreen> {
                        val viewModel = hiltViewModel<DetailPlanetViewModel>()
                        DetailPlanetScreen(
                            viewModel = viewModel,
                            onBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}