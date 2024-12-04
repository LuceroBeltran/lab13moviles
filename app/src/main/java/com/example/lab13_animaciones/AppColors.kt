package com.example.lab13_animaciones

import androidx.compose.ui.graphics.Color

// Definición de colores para ambos modos
data class AppColors(
    val backgroundColor: Color,
    val textColor: Color,
    val boxColorExpanded: Color,
    val boxColorCollapsed: Color
)

val LightColors = AppColors(
    backgroundColor = Color.White,
    textColor = Color.Black,
    boxColorExpanded = Color(0xFFEA557E),
    boxColorCollapsed = Color(0xFF1976D2)
)

val DarkColors = AppColors(
    backgroundColor = Color(0xFF303030),
    textColor = Color.White,
    boxColorExpanded = Color(0xFFFF8A65),
    boxColorCollapsed = Color(0xFF64B5F6)
)
