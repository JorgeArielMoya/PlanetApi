package edu.ucne.planetapi.presentation.planet.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import edu.ucne.planetapi.domain.planet.model.Planet

@Composable
fun PlanetListScreen(
    viewModel: PlanetListViewModel = hiltViewModel(),
    onPlanetClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PlanetListBodyScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onPlanetClick = onPlanetClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetListBodyScreen(
    state: PlanetListUiState,
    onEvent: (PlanetListEvent) -> Unit,
    onPlanetClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Planetas Dragon Ball") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = state.filterName,
                onValueChange = { onEvent(PlanetListEvent.UpdateName(it)) },
                label = { Text("Buscar por nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            state.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(state.planetsFiltradas) { planet ->
                    PlanetItem(
                        planet = planet,
                        onClick = { onPlanetClick(planet.id) }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun PlanetItem(
    planet: Planet,
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
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(planet.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    if (planet.isDestroyed) "Destruido" else "Existente",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}