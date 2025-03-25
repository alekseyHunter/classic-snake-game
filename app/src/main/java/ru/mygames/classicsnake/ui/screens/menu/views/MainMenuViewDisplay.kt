package ru.mygames.classicsnake.ui.screens.menu.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.R
import ru.mygames.classicsnake.ui.navigation.NavScreen
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme
import ru.mygames.classicsnake.ui.theme.components.JetTextButton

@Composable
fun MainMenuViewDisplay(
    onOpenPage: (NavScreen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_snake),
            contentDescription = "",
            modifier = Modifier.size(256.dp)
        )

        Spacer(modifier = Modifier.weight(2f))

        JetTextButton(
            text = "Новая игра",
            modifier = Modifier.fillMaxWidth(),
            onClick = remember {
                {
                    onOpenPage.invoke(NavScreen.Game)
                }
            }
        )

        JetTextButton(text = "Рейтинговая таблица",
            modifier = Modifier.fillMaxWidth(),
            onClick = remember {
                {
                    onOpenPage.invoke(NavScreen.RatingTable)
                }
            }
        )

        JetTextButton(text = "Настройки",
            modifier = Modifier.fillMaxWidth(),
            onClick = remember {
                {
                    onOpenPage.invoke(NavScreen.Settings)
                }
            }
        )
    }
}


@Preview
@Composable
private fun ShowPreview() {
    ClassicSnakeTheme {
        MainMenuViewDisplay() {

        }
    }
}