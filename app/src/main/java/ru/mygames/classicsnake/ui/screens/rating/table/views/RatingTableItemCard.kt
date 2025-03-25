package ru.mygames.classicsnake.ui.screens.rating.table.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.domain.models.UserGameResult
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

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
            .background(
                MaterialTheme.colorScheme.surface, RoundedCornerShape(64.dp, 64.dp, 32.dp, 32.dp)
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
            text = result.time,
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
    ClassicSnakeTheme {
        RatingTableItemCard(
            number = 1, result =  UserGameResult(score = 150, time = "01:55")
        )
    }
}