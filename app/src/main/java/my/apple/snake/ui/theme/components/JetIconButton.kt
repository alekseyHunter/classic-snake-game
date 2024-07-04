package my.apple.snake.ui.theme.components

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import my.apple.snake.R

@Composable
fun JetIconButton(
    resId: Int,
    iconColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = ImageVector.vectorResource(id = resId),
            contentDescription = "",
            tint = iconColor
        )
    }
}

@Preview
@Composable
private fun ShowPreview() {
    JetIconButton(R.drawable.ic_launcher_background, MaterialTheme.colorScheme.surface) {

    }
}

