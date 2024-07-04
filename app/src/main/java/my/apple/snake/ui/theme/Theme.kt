package my.apple.snake.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Green500,
    secondary = Green300,
    primaryContainer = Green500,
    secondaryContainer = Green300,
    tertiaryContainer = Gray50,
    onPrimaryContainer = Color.White,
    onSecondaryContainer = Color.White,
    onTertiaryContainer = Gray300,
    background = Green50,
    surface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Green500,
    secondary = Green300,
    primaryContainer = Green500,
    secondaryContainer = Green300,
    tertiaryContainer = Gray50,
    onPrimaryContainer = Color.White,
    onSecondaryContainer = Color.White,
    onTertiaryContainer = Gray300,
    background = Green50,
    surface = Color.White
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
        typography = Typography,
        content = content
    )
}