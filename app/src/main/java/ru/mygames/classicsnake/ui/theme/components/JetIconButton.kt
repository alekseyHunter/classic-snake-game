package ru.mygames.classicsnake.ui.theme.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.microsoft.fluent.mobile.icons.R
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun JetIconButton(
    @DrawableRes resId: Int,
    iconColor: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = iconColor,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        enabled = enabled
    ) {
        Icon(imageVector = ImageVector.vectorResource(id = resId), contentDescription = "")
    }
}

@Preview
@Composable
private fun ShowPreview() {
    ClassicSnakeTheme {
        JetIconButton(
            resId = R.drawable.ic_fluent_arrow_sort_up_24_filled,
            iconColor = MaterialTheme.colorScheme.surface
        ) {

        }
    }
}

@Preview
@Composable
private fun ShowPreview2() {
    ClassicSnakeTheme {
        JetIconButton(
            resId = R.drawable.ic_fluent_arrow_sort_up_24_filled,
            iconColor = MaterialTheme.colorScheme.surface,
            enabled = false
        ) {

        }
    }
}