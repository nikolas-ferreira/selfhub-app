package digital.studioweb.selfhub_app.presentation.features.productdetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.studioweb.selfhub_app.R

@Composable
fun ProductDetailsObservationInputComponent(
    observation: String,
    onObservationChange: (String) -> Unit
) {
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_comment),
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = stringResource(R.string.product_detail_dialog_any_observation),
                color = colorResource(R.color.white_smoke),
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Text(
            text = stringResource(
                R.string.product_detail_dialog_observation_length,
                observation.length.toString()
            ),
            color = colorResource(R.color.white_smoke),
            fontSize = 14.sp,
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
    OutlinedTextField(
        value = observation,
        onValueChange = {
            if (it.length <= 140) onObservationChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = {
            Text(
                text = stringResource(R.string.product_detail_dialog_observation_placeholder),
                fontSize = 14.sp
            )
        },
        maxLines = 4,
        singleLine = false,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
            unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.Gray,
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,
            disabledPlaceholderColor = Color.Gray,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 14.sp)
    )
}

@Composable
@Preview(showBackground = true)
private fun ProductDetailsObservationInputComponentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.app_background))
    ) {
        ProductDetailsObservationInputComponent(
            observation = "Sem cebola",
            onObservationChange = {}
        )
    }
}