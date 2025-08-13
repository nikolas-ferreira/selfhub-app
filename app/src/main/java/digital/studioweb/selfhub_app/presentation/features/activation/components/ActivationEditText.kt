package digital.studioweb.selfhub_app.presentation.features.activation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.studioweb.selfhub_app.R

private fun cnpjVisualTransformation(): VisualTransformation {
    return VisualTransformation { text ->
        val digits = text.text.filter { it.isDigit() }
        val masked = buildString {
            for (i in digits.indices) {
                append(digits[i])
                if (i == 1 || i == 4) append('.')
                if (i == 7) append('/')
                if (i == 11) append('-')
            }
        }
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var extra = 0
                if (offset > 1) extra++
                if (offset > 4) extra++
                if (offset > 7) extra++
                if (offset > 11) extra++
                return offset + extra
            }

            override fun transformedToOriginal(offset: Int): Int {
                var extra = 0
                if (offset > 2) extra++
                if (offset > 5) extra++
                if (offset > 8) extra++
                if (offset > 12) extra++
                return (offset - extra).coerceAtLeast(0)
            }
        }
        TransformedText(androidx.compose.ui.text.AnnotatedString(masked), offsetMapping)
    }
}

@Composable
fun ActivationEditText(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "00.000.000/0001-00",
    isError: Boolean = false,
    enabled: Boolean = true
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (isError) Color.Red else colorResource(R.color.edit_text_border)

    Box(
        modifier = Modifier
            .widthIn(min = 300.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(R.color.edit_text_background))
            .border(1.dp, borderColor, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                val digits = newValue.filter { it.isDigit() }.take(14)
                onValueChange(digits)
            },
            enabled = enabled,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                letterSpacing = 4.sp
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            cursorBrush = SolidColor(Color.White),
            singleLine = true,
            visualTransformation = cnpjVisualTransformation(),
            modifier = Modifier
                .onFocusChanged { isFocused = it.isFocused }
                .background(Color.Transparent),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            color = colorResource(R.color.edit_text_text),
                            fontSize = 18.sp,
                            letterSpacing = 2.sp
                        )
                    )
                }
                innerTextField()
            }
        )
    }
}
