package com.example.tarea4_consumirapiplanets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tarea4_consumirapiplanets.presentation.list.ListPLanetScreen
import com.example.tarea4_consumirapiplanets.ui.theme.Tarea4_ConsumirApiPlanetsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tarea4_ConsumirApiPlanetsTheme {
                ListPLanetScreen(
                    viewModel = hiltViewModel(),
                    onPlanetClick = { _ ->
                        // TODO: Navegar a detalles
                    }
                )
            }
        }
    }
}