package ru.mygames.classicsnake.ui.theme.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.microsoft.fluent.mobile.icons.R
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun JetCircleProgressBar(
    partCount: Int,
    selectedPartCount: Int,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val selectedPartColor = MaterialTheme.colorScheme.secondary
    val unselectedPartColor = MaterialTheme.colorScheme.onSecondaryContainer

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
            val paddingAngle = 16f
            val sweepAngle = (360f - partCount * paddingAngle) / partCount

            repeat(partCount) { part ->
                drawArc(
                    color = if (part < selectedPartCount) {
                        selectedPartColor
                    } else {
                        unselectedPartColor
                    },
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx())
                )
                startAngle += sweepAngle + paddingAngle
            }
        }

        content()
    }
}

@Preview
@Composable
private fun ShowPreview() {
    ClassicSnakeTheme {
        JetCircleProgressBar(
            partCount = 4,
            selectedPartCount = 1,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_fluent_heart_24_filled),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}