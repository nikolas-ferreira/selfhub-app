package digital.studioweb.selfhub_app.presentation.features.productdetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationOptionModel
import digital.studioweb.selfhub_app.presentation.utils.StringUtils.formatToBRLCurrency


@Preview(showBackground = true, backgroundColor = 0xFF0E1733)
@Composable
private fun Preview() {
    AddItemCheckComponent(
        addItem = CustomizationOptionModel(name = "Batata", additionalPrice = 10.0, id = "1"),
        quantity = 1,
        onIncrement = {},
        onDecrement = {}
    )
}

@Composable
fun AddItemCheckComponent(
    addItem: CustomizationOptionModel,
    quantity: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 1.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = addItem.name,
            color = colorResource(R.color.white_seashell),
            fontSize = 16.sp,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = formatToBRLCurrency(addItem.additionalPrice),
                color = colorResource(R.color.white_seashell),
                fontSize = 12.sp,
                modifier = Modifier.padding(end = 6.dp)
            )
            ProductCount(
                quantity = quantity,
                onIncrement = onIncrement,
                onDecrement = onDecrement
            )
        }
    }
}

