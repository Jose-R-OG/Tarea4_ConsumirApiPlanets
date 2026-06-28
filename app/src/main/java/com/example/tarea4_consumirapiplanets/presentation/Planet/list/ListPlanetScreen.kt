package com.example.tarea4_consumirapiplanets.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.tarea4_consumirapiplanets.domain.model.Planets
import com.example.tarea4_consumirapiplanets.presentation.Planet.list.ListPlanetUiEvent
import com.example.tarea4_consumirapiplanets.presentation.Planet.list.ListPlanetUiState
import com.example.tarea4_consumirapiplanets.presentation.Planet.list.ListPlanetViewModel

@Composable
fun ListPLanetScreen(
    viewModel: ListPlanetViewModel,
    onPlanetClick: (Int) -> Unit,
    onOpenDrawer: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ListPlanetBodyScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onPlanetClick = onPlanetClick,
        onOpenDrawer = onOpenDrawer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPlanetBodyScreen(
    state: ListPlanetUiState,
    onEvent: (ListPlanetUiEvent) -> Unit,
    onPlanetClick: (Int) -> Unit,
    onOpenDrawer: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Planetas Dragon Ball") },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Abrir Menú")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            FilterPlanetSection(
                state = state,
                onEvent = onEvent
            )

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }

            state.error?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.planets) { planet ->
                    PlanetItem(
                        planet = planet,
                        onClick = { onPlanetClick(planet.id) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun FilterPlanetSection(
    state: ListPlanetUiState,
    onEvent: (ListPlanetUiEvent) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = state.nameFilter,
                onValueChange = {
                    onEvent(ListPlanetUiEvent.UpdateFilters(it, state.isDestroyedFilter ))
                },
                label = { Text("Nombre del planeta") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Button(
                onClick = { onEvent(ListPlanetUiEvent.Search) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Buscar")
            }
        }
    }
}

@Composable
fun PlanetItem(
    planet: Planets,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = planet.image,
                contentDescription = planet.name,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = planet.name, style = MaterialTheme.typography.titleMedium)

                val statusText = if (planet.isDestroyed == true) "Destruido" else "Intacto"
                val statusColor = if (planet.isDestroyed == true) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant

                Text(
                    text = "Estado: $statusText",
                    style = MaterialTheme.typography.bodyMedium,
                    color = statusColor
                )
            }
        }
    }
}