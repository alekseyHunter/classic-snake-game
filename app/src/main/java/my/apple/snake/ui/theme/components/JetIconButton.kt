package my.apple.snake.ui.theme.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.apple.snake.ui.theme.AppleSnakeTheme

@Composable
fun JetIconButton(
    resId: Int, iconColor: Color, modifier: Modifier = Modifier, onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = iconColor
        )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = resId),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
private fun ShowPreview() {
    AppleSnakeTheme {
        JetIconButton(
            com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_arrow_sort_up_24_filled,
            MaterialTheme.colorScheme.surface
        ) {

        }
    }
}

