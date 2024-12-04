package com.example.lab13_animaciones

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
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

@Composable
fun AppAnimacion() {
    var isLightMode by remember { mutableStateOf(true) }
    val colors = if (isLightMode) LightColors else DarkColors

    // Color de fondo animado que cambia entre claro y oscuro
    val backgroundColor by animateColorAsState(
        targetValue = colors.backgroundColor,
        animationSpec = tween(durationMillis = 600),
        label = "BackgroundColorAnimation"
    )
    var isButtonVisible by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Switch para alternar entre modo claro y oscuro
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Modo Claro/Oscuro", color = colors.textColor)
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = !isLightMode, onCheckedChange = { isLightMode = !isLightMode })
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Botón animado con desplazamiento y AnimatedVisibility
            AnimatedVisibility(
                visible = isButtonVisible,
                enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { 100 }) + fadeOut()
            ) {
                Button(onClick = { isButtonVisible = false }) {
                    Text("Desaparecer")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            SizeAndPositionAnimation(
                textColor = colors.textColor,
                boxColorExpanded = colors.boxColorExpanded,
                boxColorCollapsed = colors.boxColorCollapsed
            )  // Aquí se llama directamente a SizeAndPositionAnimation
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility()  // Cambiado a AnimatedVisibilityExample para que coincida
            Spacer(modifier = Modifier.height(16.dp))
            ColorAnimation()  // Aquí se llama directamente a ColorAnimation
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedContent()
        }
    }
}