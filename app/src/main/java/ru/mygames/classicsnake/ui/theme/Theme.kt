package ru.mygames.classicsnake.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = darkPrimaryColor,
    secondary = darkSecondaryColor,
    primaryContainer = darkPrimaryColor,
    secondaryContainer = darkSecondaryColor,
    tertiaryContainer = darkTertiaryColor,
    onPrimaryContainer = darkOnPrimaryColor,
    onSecondaryContainer = darkOnSecondaryColor,
    onTertiaryContainer = darkOnTertiaryColor,
    background = darkBackgroundColor,
    surface = darkSurfaceColor
)

private val LightColorScheme = lightColorScheme(
    primary = lightPrimaryColor,
    secondary = lightSecondaryColor,
    primaryContainer = lightPrimaryColor,
    secondaryContainer = lightOnSurfaceColor,
    tertiaryContainer = lightTertiaryColor,
    onPrimaryContainer = lightOnPrimaryColor,
    onSecondaryContainer = lightOnSecondaryColor,
    onTertiaryContainer = lightOnTertiaryColor,
    background = lightBackgroundColor,
    surface = lightSurfaceColor
)

@Composable
fun ClassicSnakeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}