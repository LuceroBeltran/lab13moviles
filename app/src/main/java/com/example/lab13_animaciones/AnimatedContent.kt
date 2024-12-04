package com.example.lab13_animaciones

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Definición de estados posibles
sealed class ContentState {
    object Loading : ContentState()
    data class Success(val data: String) : ContentState()
    data class Error(val message: String) : ContentState()
}

@Composable
fun AnimatedContent() {
    var contentState by remember { mutableStateOf<ContentState>(ContentState.Loading) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // AnimatedContent con transiciones de fadeIn y fadeOut
        AnimatedContent(
            targetState = contentState,
            transitionSpec = {
                fadeIn(animationSpec = tween(1000)) togetherWith fadeOut(animationSpec = tween(1000))
            },
            label = "ContentTransition"
        ) { state ->
            when (state) {
                is ContentState.Loading -> {
                    LoadingContent()
                }
                is ContentState.Success -> {
                    SuccessContent(state.data)
                }
                is ContentState.Error -> {
                    ErrorContent(state.message)
                }
            }
        }

        // Botón para cambiar estados
        Button(
            onClick = {
                contentState = when (contentState) {
                    is ContentState.Loading -> ContentState.Success("Datos cargados exitosamente")
                    is ContentState.Success -> ContentState.Error("Ocurrió un error en la carga")
                    is ContentState.Error -> ContentState.Loading
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Cambiar Estado")
        }
    }
}




// Componente de Carga
@Composable
fun LoadingContent() {
    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Text(
                text = "Cargando...",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

// Componente de Éxito
@Composable
fun SuccessContent(data: String) {
    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Éxito!",
                color = Color.Green,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = data,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

// Componente de Error
@Composable
fun ErrorContent(message: String) {
    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Error",
                color = Color.Red,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = message,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

