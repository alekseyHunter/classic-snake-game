package my.apple.snake.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 0f,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.throttleClickable(delay: Long = 500L, onClick: () -> Unit): Modifier = composed(
    inspectorInfo = {
        name = "throttleClickable"
        value = onClick
    }
) {
    var enableAgain by remember { mutableStateOf(true) }
    LaunchedEffect(enableAgain, block = {
        if (enableAgain) return@LaunchedEffect
        delay(timeMillis = delay)
        enableAgain = true
    })
    Modifier.clickable {
        if (enableAgain) {
            enableAgain = false
            onClick()
        }
    }
}