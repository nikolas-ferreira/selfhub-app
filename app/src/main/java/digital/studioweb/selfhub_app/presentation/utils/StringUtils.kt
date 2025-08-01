package digital.studioweb.selfhub_app.presentation.utils

import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object StringUtils {
    fun formatToBRLCurrency(price: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return format.format(price)
    }

    fun formatCurrentDate(): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale("pt", "BR"))
        return today.format(formatter).replace(".", "").replaceFirstChar { it.uppercase() }
    }

    fun formatCurrentTime(): String {
        val zoneId = ZoneId.of("America/Sao_Paulo")
        val now = LocalTime.now(zoneId)
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return now.format(timeFormatter)
    }
}