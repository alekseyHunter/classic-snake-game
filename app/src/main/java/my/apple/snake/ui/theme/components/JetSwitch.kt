package my.apple.snake.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
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
import my.apple.snake.ui.theme.AppleSnakeTheme

@Composable
fun JetSwitch(
    items: List<String>,
    selectedItemId: Int,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(32.dp),
    onChange: (Int) -> Unit
) {
    val selectedItem = items.getOrNull(selectedItemId) ?: ""

    val isActivePreviousButton = selectedItemId > 0
    val isActiveNextButton = selectedItemId < items.lastIndex

    val previousButtonContainer = if (isActivePreviousButton) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }
    val nextButtonContainer = if (isActiveNextButton) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }
    val previousButtonContent = if (isActivePreviousButton) {
        MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        MaterialTheme.colorScheme.onTertiaryContainer
    }
    val nextButtonContent = if (isActiveNextButton) {
        MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        MaterialTheme.colorScheme.onTertiaryContainer
    }

    Box(
        modifier = modifier
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(MaterialTheme.colorScheme.surface, shape)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primaryContainer.copy(0.25f),
                    shape
                )
                .align(Alignment.Center)
        ) {
            Text(
                text = selectedItem.uppercase(),
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    previousButtonContainer, RoundedCornerShape(
                        topStart = shape.topStart,
                        bottomStart = shape.bottomStart,
                        topEnd = CornerSize(8.dp),
                        bottomEnd = CornerSize(8.dp)
                    )
                )
                .align(Alignment.CenterStart)
                .clip(
                    RoundedCornerShape(
                        topStart = shape.topStart,
                        bottomStart = shape.bottomStart,
                        topEnd = CornerSize(8.dp),
                        bottomEnd = CornerSize(8.dp)
                    )
                )
                .clickable {
                    if (isActivePreviousButton) {
                        onChange.invoke(selectedItemId - 1)
                    }
                }
            ,
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_chevron_left_16_filled
                ),
                contentDescription = "",
                tint = previousButtonContent,
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    nextButtonContainer, RoundedCornerShape(
                        topEnd = shape.topEnd,
                        bottomEnd = shape.bottomEnd,
                        topStart = CornerSize(8.dp),
                        bottomStart = CornerSize(8.dp)
                    )
                )
                .align(Alignment.CenterEnd)
                .clip(
                    RoundedCornerShape(
                        topEnd = shape.topEnd,
                        bottomEnd = shape.bottomEnd,
                        topStart = CornerSize(8.dp),
                        bottomStart = CornerSize(8.dp)
                    )
                )
                .clickable {
                    if (isActiveNextButton) {
                        onChange.invoke(selectedItemId + 1)
                    }
                }
            , contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_chevron_right_16_filled
                ),
                contentDescription = "",
                tint = nextButtonContent,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ShowPreview() {
    AppleSnakeTheme {
        JetSwitch(
            modifier = Modifier.fillMaxWidth(),
            items = listOf("Легко", "Нормально", "Сложно"),
            selectedItemId = 0
        ) {}
    }
}


@Preview
@Composable
private fun ShowPreview2() {
    AppleSnakeTheme {
        JetSwitch(
            modifier = Modifier.fillMaxWidth(),
            items = listOf("Нормально"),
            selectedItemId = 0
        ) {}
    }
}
