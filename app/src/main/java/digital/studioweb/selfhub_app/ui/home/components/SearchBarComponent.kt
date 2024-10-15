package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import digital.studioweb.selfhub_app.R


//TODO adicionar isso aqui no futuro
@Preview(showBackground = true)
@Composable
fun SearchBarComponent() {
//    var text by remember { mutableStateOf("") }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(40.dp)
//            .border(
//                width = 2.dp,
//                color = colorResource(id = R.color.search_bar_border),
//                shape = RoundedCornerShape(20.dp)
//            )
//    ) {
//        Row(
//            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            TextField(
//                value = text,
//                onValueChange = { newText -> text = newText },
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(horizontal = 8.dp),
//                placeholder = { Text("Pesquise algo gostoso que esteja na sua cabeça...") },
//
////                colors = TextFieldDefaults.textFieldColors(
////                    backgroundColor = Color.Transparent, // Fundo transparente
////                    focusedIndicatorColor = Color.Transparent, // Remove a linha inferior
////                    unfocusedIndicatorColor = Color.Transparent
////                )
//            )
//
//            // Botão com ícone de pesquisa
//            IconButton(
//                onClick = { }
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_menu_category), // Substitua pelo seu ícone de pesquisa
//                    contentDescription = "Pesquisar",
//
//                    )
//            }
//        }
//    }
}