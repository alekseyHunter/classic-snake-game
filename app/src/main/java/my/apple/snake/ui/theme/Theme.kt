package my.apple.snake.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = darkPrimaryColor,
    secondary = darkSecondaryColor,
    primaryContainer = darkPrimaryColor,
    secondaryContainer = darkSecondaryColor,
    tertiaryContainer = darkTertiaryColor,
    onPrimaryContainer = darkOnPrimaryColor,
    onSecondaryContainer = Color.White,
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
    onSecondaryContainer = Color.White,
    onTertiaryContainer = lightOnTertiaryColor,
    background = lightBackgroundColor,
    surface = lightSurfaceColor
)

private val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(32.dp)
)

@Composable
fun AppleSnakeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}