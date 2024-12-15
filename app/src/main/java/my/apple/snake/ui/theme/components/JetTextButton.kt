package my.apple.snake.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.apple.snake.ui.theme.AppleSnakeTheme
import my.apple.snake.utils.throttleClickable

@Composable
fun JetTextButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(32.dp),
    enabled: Boolean = true,
    onClick: () -> Unit,

) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer, shape)
            .clip(shape)
            .then(if (enabled) Modifier.throttleClickable(onClick = onClick) else Modifier)
            .padding(PaddingValues(32.dp, 12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview
@Composable
private fun ShowPreview() {
    AppleSnakeTheme {
        JetTextButton(text = "Новая игра") {
        }
    }
}

data class OrderDetails(
    val id: Int, val items: List<String>, val cost: Double
)

@Composable
fun OrderCard(order: OrderDetails) {
    Box(
        Modifier
            .padding(16.dp)
            .size(256.dp, 196.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .border(1.dp, Color(0xFF5694F0), RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {

    }
}

@Preview
@Composable
private fun ShowPreview2() {
    OrderCard(OrderDetails(1, listOf("", ""), 150.0))
}