package digital.studioweb.selfhub_app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import digital.studioweb.selfhub_app.R

@Composable
fun DashedDivider(
    color: Color = colorResource(R.color.divider_color),
    modifier: Modifier = Modifier,
    dashLength: Float = 32f,
    gapLength: Float = 22f,
    thickness: Float = 8f
) {
    Canvas(modifier = modifier.fillMaxWidth()) {
        val totalWidth = size.width
        var currentX = 0f
        while (currentX < totalWidth) {
            drawLine(
                color = color,
                start = Offset(currentX, size.height / 2),
                end = Offset(currentX + dashLength, size.height / 2),
                strokeWidth = thickness
            )
            currentX += dashLength + gapLength
        }
    }
}
