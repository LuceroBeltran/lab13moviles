package com.example.lab13_animaciones

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun SizeAndPositionAnimation(
    textColor: Color,
    boxColorExpanded: Color,
    boxColorCollapsed: Color
) {
    var expanded by remember { mutableStateOf(false) }

    // Offset para mover el cuadro desde la izquierda hacia el centro
    val offsetX by animateDpAsState(
        targetValue = if (expanded) 0.dp else (-150).dp, // Posición inicial a la izquierda, expandido en el centro
        label = "offsetX"
    )

    // Altura del cuadro animada entre 100.dp y 200.dp
    val boxHeight by animateDpAsState(
        targetValue = if (expanded) 200.dp else 100.dp,
        label = "boxHeight"
    )
    // Animación de color del Box
    val boxColor by animateColorAsState(
        targetValue = if (expanded) boxColorExpanded else boxColorCollapsed,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "BoxColorAnimation"
    )

    // Ancho del cuadro, alternando entre 100.dp y fillMaxWidth
    val boxModifier = if (expanded) Modifier.fillMaxWidth() else Modifier.width(100.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Mover y Cambiar Tamaño",
            color = textColor)

        Spacer(modifier = Modifier.height(16.dp))

        // Cuadro que cambia de posición y tamaño
        Box(
            modifier = Modifier
                .offset(x = offsetX) // Aplicar el desplazamiento en X
                .then(boxModifier) // Ancho inicial o expandido
                .height(boxHeight) // Ajuste de la altura
                .animateContentSize() // Animar el cambio de tamaño suavemente
                .background(boxColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    expanded = !expanded
                }
        )
    }
}

