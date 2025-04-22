package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.data.models.AddItems


@Composable
fun AddItemCheckComponent(addItem: AddItems) {
    Row(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = addItem.name,
            fontSize = 16.sp,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                color = colorResource(R.color.product_component_order_button_background),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .size(16.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.dp)
                        .clickable { }

                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "0",
                color = colorResource(R.color.dark_color),
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Surface(
                color = colorResource(R.color.product_component_order_button_background),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .size(16.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.dp)
                        .clickable { }

                )
            }
        }
    }
}