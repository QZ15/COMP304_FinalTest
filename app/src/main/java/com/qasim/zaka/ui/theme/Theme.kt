package com.qasim.zaka.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Dark theme colors with no purple, using a blue accent.
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF2196F3),     // Blue accent
    onPrimary = Color.White,
    secondary = Color(0xFF03A9F4),   // Another shade of blue
    onSecondary = Color.White,
    background = Color(0xFF121212),  // Dark background
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),     // Dark grey surface
    onSurface = Color.White
)

// Light theme colors without purple, also using blue.
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2196F3),     // Blue accent
    onPrimary = Color.White,
    secondary = Color(0xFF03A9F4),   // Another shade of blue
    onSecondary = Color.White,
    background = Color(0xFF121212),  // Dark background
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),     // Dark grey surface
    onSurface = Color.White
)

@Composable
fun QasimZaka_COMP304_FinalTest_F24Theme(
    darkTheme: Boolean = true, // Defaults to dark theme
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
