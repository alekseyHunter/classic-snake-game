package my.apple.snake.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.apple.snake.R
import my.apple.snake.ui.theme.AppleSnakeTheme

@Composable
fun JetCheckBox(
    checked: Boolean,
    hint: String,
    modifier: Modifier = Modifier,
    onChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.secondaryContainer,
                    RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onChange.invoke(!checked)
                },
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_checkmark_24_filled),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Text(text = hint, color = MaterialTheme.colorScheme.primary)
    }
}

@Preview
@Composable
private fun ShowPreview() {
    AppleSnakeTheme {
        JetCheckBox(true, "Урон от столкновения со стенами") {

        }
    }
}