package digital.studioweb.selfhub_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import digital.studioweb.selfhub_app.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun CalendarComponent() {
    Box(
        modifier = Modifier
            .height(45.dp)
            .background(Color.White, shape = RoundedCornerShape(50.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_calendar),
                colorFilter = ColorFilter.tint(
                    colorResource(
                        id = R.color.icon_menu_category_item_card_selected_background
                    )
                ),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))

            Text(formatCurrentDate())
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}


private fun formatCurrentDate(): String {
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale("pt", "BR"))
    return today.format(formatter).replace(".", "").replaceFirstChar { it.uppercase() }
}
