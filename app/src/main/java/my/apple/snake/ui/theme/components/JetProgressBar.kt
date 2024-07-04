package my.apple.snake.ui.theme.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JetProgressBar(
    modifier: Modifier = Modifier,
    partCount: Int,
    selectedPartCount: Int,
    strokeWidth: Dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(strokeWidth / 2)
        ) {
            var startAngle = -90f
            val sweepAngle = (360f - partCount * 16f) / partCount
            repeat(partCount) { part ->
                drawArc(
                    color = if (part < selectedPartCount) Color(0xFFFA743E) else Color(0xFFCCD6DD),
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx())
                )
                startAngle += sweepAngle + 16
            }
        }

        content()
    }
}

@Preview
@Composable
private fun ShowPreview() {
    JetProgressBar(Modifier.size(48.dp), 4, 1, 8.dp) {

    }
}