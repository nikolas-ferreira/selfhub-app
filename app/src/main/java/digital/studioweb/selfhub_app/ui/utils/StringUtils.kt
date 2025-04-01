package digital.studioweb.selfhub_app.ui.utils

import java.text.NumberFormat
import java.util.Locale

object StringUtils {
    fun formatToBRLCurrency(price: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return format.format(price)
    }
}