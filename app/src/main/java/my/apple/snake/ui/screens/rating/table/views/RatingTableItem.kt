package my.apple.snake.ui.screens.rating.table.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.microsoft.fluent.mobile.icons.R
import my.apple.snake.data.local.datastore.UserGameResult
import my.apple.snake.ui.theme.AppleSnakeTheme


@Composable
fun RatingTableCard(
    results: List<UserGameResult>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                2.dp,
                MaterialTheme.colorScheme.primaryContainer.copy(0.25f),
                RoundedCornerShape(
                    topStart = 64.dp,
                    topEnd = 64.dp,
                    bottomStart = 32.dp,
                    bottomEnd = 32.dp
                )
            )
            .background(
                MaterialTheme.colorScheme.onSecondaryContainer,
                RoundedCornerShape(
                    topStart = 64.dp,
                    topEnd = 64.dp,
                    bottomStart = 32.dp,
                    bottomEnd = 32.dp
                )
            )
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Место",
                color = MaterialTheme.colorScheme.primary.copy(0.5f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Кол-во очков",
                color = MaterialTheme.colorScheme.primary.copy(0.5f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Время",
                color = MaterialTheme.colorScheme.primary.copy(0.5f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        if (results.isNotEmpty()) {
            results.forEachIndexed { index, result ->
                RatingTableItemCard(index + 1, result)
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_fluent_calendar_empty_24_filled),
                        contentDescription = "",
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.tertiaryContainer
                    )
                    Text(
                        text = "Статистика не найдена",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ShowRatingTableCardPreview() {
    AppleSnakeTheme {
        RatingTableCard(results = emptyList())
    }
}

@Composable
fun RatingTableItemCard(
    number: Int,
    result: UserGameResult,
    modifier: Modifier = Modifier
) {

    val textColor = when (number) {
        1 -> MaterialTheme.colorScheme.primary
        2 -> MaterialTheme.colorScheme.primary.copy(0.85f)
        3 -> MaterialTheme.colorScheme.primary.copy(0.75f)
        else -> MaterialTheme.colorScheme.primary.copy(0.5f)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(64.dp, 64.dp, 32.dp, 32.dp)
            )
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "$number",
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "${result.score}",
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "${result.time}",
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun ShowPreview() {
    AppleSnakeTheme {
        RatingTableItemCard(1, UserGameResult(10, "01:00"))
    }
}