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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import digital.studioweb.selfhub_app.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Preview(showBackground = true)
@Composable
fun ClockComponent() {
    var currentTime by remember { mutableStateOf(formatCurrentTime()) }
    
    LaunchedEffect(Unit) {
        while(true) {
            currentTime = formatCurrentTime()
            delay(1000)
        }
    }
    
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
                painter = painterResource(id = R.drawable.ic_clock),
                colorFilter = ColorFilter.tint(
                    colorResource(
                        id = R.color.primary_orange
                    )
                ),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))

            Text(currentTime)
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}

private fun formatCurrentTime(): String {
    val now = LocalTime.now()
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return now.format(timeFormatter)
}
