package my.apple.snake.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    label: String? = null,
    onChange: (Int) -> Unit
) {
    val selectedItem = items.getOrNull(selectedItemId) ?: ""

    val isActivePreviousButton = selectedItemId > 0
    val isActiveNextButton = selectedItemId < items.lastIndex

    val containerPreviousButton = if (isActivePreviousButton) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }
    val containerNextButton = if (isActiveNextButton) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }
    val contentPreviousButton = if (isActivePreviousButton) {
        MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        MaterialTheme.colorScheme.onTertiaryContainer
    }
    val contentNextButton = if (isActiveNextButton) {
        MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        MaterialTheme.colorScheme.onTertiaryContainer
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        label?.let {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color.White, RoundedCornerShape(32.dp))
                    .border(
                        2.dp,
                        MaterialTheme.colorScheme.secondaryContainer,
                        RoundedCornerShape(32.dp)
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
                        containerPreviousButton, RoundedCornerShape(
                            topStart = 32.dp, bottomStart = 32.dp, topEnd = 8.dp, bottomEnd = 8.dp
                        )
                    )
                    .align(Alignment.CenterStart)
                    .clickable {
                        if (isActivePreviousButton) {
                            onChange.invoke(selectedItemId - 1)
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_chevron_left_16_filled
                    ),
                    contentDescription = "",
                    tint = contentPreviousButton,
                    modifier = Modifier.size(24.dp)
                )
            }

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        containerNextButton, RoundedCornerShape(
                            topEnd = 32.dp, bottomEnd = 32.dp, topStart = 8.dp, bottomStart = 8.dp
                        )
                    )
                    .align(Alignment.CenterEnd)
                    .clickable {
                        if (isActiveNextButton) {
                            onChange.invoke(selectedItemId + 1)
                        }
                    }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_chevron_right_16_filled
                    ),
                    contentDescription = "",
                    tint = contentNextButton,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShowPreview() {
    AppleSnakeTheme {
        JetSwitch(
            modifier = Modifier.fillMaxWidth(), items = listOf("Нормально"), selectedItemId = 0
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
            selectedItemId = 0,
            label = "Уровень сложности"
        ) {}
    }
}
