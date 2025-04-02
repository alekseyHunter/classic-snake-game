package ru.mygames.classicsnake.ui.screens.game.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.microsoft.fluent.mobile.icons.R
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme
import ru.mygames.classicsnake.ui.theme.components.JetCircleProgressBar
import ru.mygames.classicsnake.ui.theme.components.JetCounter

@Composable
fun GameTopBar(
    sessionTime: String,
    score: Int,
    currentSnakeLives: Int,
    maxSnakeLives: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.onSecondaryContainer.copy(0.15f),
                    RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            JetCounter(resId = R.drawable.ic_fluent_time_picker_24_filled, value = sessionTime)

            JetCounter(resId = R.drawable.ic_fluent_star_24_filled, value = score.toString())
        }

        JetCircleProgressBar(
            partCount = maxSnakeLives,
            selectedPartCount = currentSnakeLives,
            Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_fluent_heart_24_filled),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview
@Composable
private fun ShowPreview() {
    ClassicSnakeTheme {
        GameTopBar(
            sessionTime = "01:55",
            score = 150,
            currentSnakeLives = 2,
            maxSnakeLives = 4,
            modifier = Modifier.fillMaxWidth()
        )
    }
}