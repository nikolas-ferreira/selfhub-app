//package digital.studioweb.selfhub_app.ui.home.components
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import digital.studioweb.selfhub_app.R
//import digital.studioweb.selfhub_app.data.models.AddItem
//import digital.studioweb.selfhub_app.ui.utils.StringUtils.formatToBRLCurrency
//
//
//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    AddItemCheckComponent(AddItem("Batata", 10.0))
//}
//
//@Composable
//fun AddItemCheckComponent(addItem: AddItem) {
//    Row(
//        modifier = Modifier
//            .padding(vertical = 1.dp)
//            .fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = addItem.name,
//            fontSize = 16.sp,
//        )
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Text(
//                text = formatToBRLCurrency(addItem.price),
//                color = colorResource(R.color.dark_color),
//                fontSize = 12.sp,
//                modifier = Modifier.padding(end = 6.dp)
//            )
//            ProductCount()
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ProductCount() {
//    Box(
//        modifier = Modifier
//            .padding(4.dp)
//            .background(
//                color = colorResource(R.color.add_item_component_background),
//                shape = RoundedCornerShape(20.dp)
//            )
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(5.dp)
//        ) {
//            Surface(
//                color = colorResource(R.color.white),
//                shape = RoundedCornerShape(50),
//                modifier = Modifier
//                    .size(16.dp)
//
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_remove),
//                    contentDescription = null,
//                    colorFilter = ColorFilter.tint(colorResource(R.color.dark_color)),
//                    modifier = Modifier
//                        .padding(1.dp)
//                        .clickable { }
//
//                )
//            }
//            Spacer(modifier = Modifier.width(12.dp))
//            Text(
//                text = "0",
//                color = colorResource(R.color.dark_color),
//                fontSize = 12.sp,
//            )
//            Spacer(modifier = Modifier.width(12.dp))
//            Surface(
//                color = colorResource(R.color.product_component_order_button_background),
//                shape = RoundedCornerShape(50),
//                modifier = Modifier
//                    .size(16.dp)
//
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_add),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(1.dp)
//                        .clickable { }
//
//                )
//            }
//        }
//    }
//}