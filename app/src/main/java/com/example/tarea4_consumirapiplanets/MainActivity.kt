package com.example.tarea4_consumirapiplanets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.tarea4_consumirapiplanets.presentation.Planet.list.ListPlanetViewModel
import com.example.tarea4_consumirapiplanets.presentation.character.detail.DetailCharacterScreen
import com.example.tarea4_consumirapiplanets.presentation.character.detail.DetailCharacterViewModel
import com.example.tarea4_consumirapiplanets.presentation.character.list.ListCharacterScreen
import com.example.tarea4_consumirapiplanets.presentation.character.list.ListCharacterViewModel
import com.example.tarea4_consumirapiplanets.presentation.detail.DetailPlanetScreen
import com.example.tarea4_consumirapiplanets.presentation.detail.DetailPlanetViewModel
import com.example.tarea4_consumirapiplanets.presentation.list.ListPLanetScreen
import com.example.tarea4_consumirapiplanets.presentation.navigation.*
import com.example.tarea4_consumirapiplanets.ui.theme.Tarea4_ConsumirApiPlanetsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tarea4_ConsumirApiPlanetsTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(Modifier.height(16.dp))
                            Text("Universo Dragon Ball", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                            HorizontalDivider()

                            // Botón para ir a Planetas
                            NavigationDrawerItem(
                                label = { Text("Planetas") },
                                selected = false,
                                onClick = {
                                    navController.navigate(ListScreen) {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )

                            // Botón para ir a Personajes
                            NavigationDrawerItem(
                                label = { Text("Personajes") },
                                selected = false,
                                onClick = {
                                    navController.navigate(CharacterListScreen) {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = ListScreen // Pantalla inicial
                    ) {

                        composable<ListScreen> {
                            val viewModel = hiltViewModel<ListPlanetViewModel>()
                            ListPLanetScreen(
                                viewModel = viewModel,
                                onPlanetClick = { planetId ->
                                    navController.navigate(DetailScreen(id = planetId))
                                },
                                onOpenDrawer = { scope.launch { drawerState.open() } }
                            )
                        }

                        composable<DetailScreen> {
                            val viewModel = hiltViewModel<DetailPlanetViewModel>()
                            DetailPlanetScreen(
                                viewModel = viewModel,
                                onBack = { navController.navigateUp() }
                            )
                        }


                        composable<CharacterListScreen> {
                            val viewModel = hiltViewModel<ListCharacterViewModel>()
                            ListCharacterScreen(
                                viewModel = viewModel,
                                onCharacterClick = { charId -> navController.navigate(CharacterDetailScreen(id = charId)) },
                                onOpenDrawer = { scope.launch { drawerState.open() } }
                            )
                        }

                        composable<CharacterDetailScreen> {
                            val viewModel = hiltViewModel<DetailCharacterViewModel>()
                            DetailCharacterScreen(
                                viewModel = viewModel,
                                onBack = { navController.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}