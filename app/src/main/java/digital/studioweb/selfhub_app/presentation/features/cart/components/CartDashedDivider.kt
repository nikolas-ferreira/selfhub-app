package digital.studioweb.selfhub_app.presentation.features.cart.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import digital.studioweb.selfhub_app.R

@Composable
fun CartDashedDivider(
    color: Color = colorResource(R.color.divider_color),
    modifier: Modifier = Modifier,
    dashLength: Dp = 12.dp,
    gapLength: Dp = 6.dp,
    thickness: Dp = 1.dp
) {
    val density = LocalDensity.current

    Canvas(modifier = modifier.fillMaxWidth()) {
        val totalWidth = size.width
        var currentX = 0f

        val dashPx = with(density) { dashLength.toPx() }
        val gapPx = with(density) { gapLength.toPx() }
        val strokePx = with(density) { thickness.toPx() }

        while (currentX < totalWidth) {
            drawLine(
                color = color,
                start = Offset(currentX, size.height / 2),
                end = Offset((currentX + dashPx).coerceAtMost(totalWidth), size.height / 2),
                strokeWidth = strokePx
            )
            currentX += dashPx + gapPx
        }
    }
}
