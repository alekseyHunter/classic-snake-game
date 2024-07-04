package my.apple.snake.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.apple.snake.R

@Composable
fun JetCounter(resId: Int, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(36.dp, 32.dp), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = ImageVector.vectorResource(id = resId),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.surface
            )
        }

        Text(text = value, color = MaterialTheme.colorScheme.primary)
    }
}

@Preview
@Composable
private fun ShowPreview() {
    JetCounter(resId = R.drawable.ic_launcher_background, value = "")
}