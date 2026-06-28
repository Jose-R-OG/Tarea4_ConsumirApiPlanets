package com.example.tarea4_consumirapiplanets.presentation.character.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCharacterScreen(
    viewModel: DetailCharacterViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Personaje") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            }

            state.character?.let { character ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )

                    Text(text = character.name, style = MaterialTheme.typography.headlineLarge)

                    HorizontalDivider()

                    Text(text = "Raza: ${character.race}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Genero: ${character.gender}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Ki Base: ${character.ki}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Ki Máximo: ${character.maxKi}", style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Descripcion", style = MaterialTheme.typography.titleLarge)
                    Text(text = character.description, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}