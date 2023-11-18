package com.example.rasikrangmanch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    var menuItems by remember { mutableStateOf(getAuthenticIndianMenu()) }
    var searchPhrase by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clip(shape = RoundedCornerShape(16.dp)),
    ) {
        Text(
            text = "Menu",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
        )

        OutlinedTextField(
            value = searchPhrase,
            onValueChange = { searchPhrase = it },
            label = { Text("Search Menu") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp, bottom = 10.dp)
        )

        val filteredMenuItems = menuItems.mapValues { (_, items) ->
            items.filter { menuItem ->
                searchPhrase.split(" ").any { searchWord ->
                    menuItem.name.contains(searchWord, ignoreCase = true)
                }
            }
        }


        LazyColumn {
            menuItems.keys.forEach { category ->
                item {
                    Text(
                        text = category,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(filteredMenuItems[category] ?: emptyList()) { menuItem ->
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    MenuItem(item = menuItem)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate(Payment.route)
            },
            containerColor = Color(0xFFF55B5B),
            contentColor = Color.White
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Proceed to Pay",
                    modifier = Modifier.padding(end = 5.dp),
                )
                Text(
                    "Pay",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun MenuItem(item: MenuItem) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {

        Column {
            Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = item.description, fontSize = 14.sp)
            Text(text = "Price: ₹${item.price}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            if (item.quantity > 0) {
                QuantityCounter(
                    quantity = item.quantity,
                    onIncrement = { item.quantity++ },
                    onDecrement = {
                        item.quantity--
                        if (item.quantity == 0) {
                            item.isSelected = false
                        }
                    }
                )
            } else {
                ElevatedButton(
                    modifier = Modifier.padding(top = 10.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color(0xFFFF8A23),
                        contentColor = Color.White
                    ),
                    onClick = {
                        item.quantity++
                        item.isSelected = true
                    }
                ) {
                    Text("Add Item")
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = item.imageResourceId),
            contentDescription = "Menu Item Image",
            modifier = Modifier
                .width(120.dp)
                .height(80.dp)
        )
    }
}

@Composable
fun QuantityCounter(
    quantity: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        IconButton(onClick = { onDecrement() }) {
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Decrement")
        }
        Text(text = quantity.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp))
        IconButton(onClick = { onIncrement() }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Increment")
        }
    }
}

data class MenuItem(
    val name: String,
    val description: String,
    val price: Double,
    val imageResourceId: Int,
    var quantity: Int = 0,
    var isSelected: Boolean = false
)

fun getAuthenticIndianMenu(): Map<String, List<MenuItem>> {
    val menuItems = mapOf(
        "Appetizers" to listOf(
            MenuItem("Samosa", "Fried pastry with a savory filling", 190.00, imageResourceId = R.drawable.samosa),
            MenuItem("Paneer Tikka", "Marinated paneer cubes", 350.00, imageResourceId = R.drawable.paneer_tikka),
            MenuItem("Pakora", "Fried vegetable fritters", 220.00, imageResourceId = R.drawable.pakora),
            MenuItem("Dahi Puri", "Crispy snack with curd, chutneys", 180.00, imageResourceId = R.drawable.dahi_puri),
            MenuItem("Aloo Tikki", "Fried potato patties", 160.00, imageResourceId = R.drawable.aloo_tikki),
        ),
        "Main Courses" to listOf(
            MenuItem("Butter Chicken", "Creamy tomato sauce with chicken", 750.00, imageResourceId = R.drawable.butter_chicken),
            MenuItem("Biryani", "Fragrant rice dish with spices", 550.00, imageResourceId = R.drawable.biryani),
            MenuItem("Palak Paneer", "Spinach curry with paneer", 450.00, imageResourceId = R.drawable.palak_paneer),
            MenuItem("Chicken Tikka Masala", "Grilled chicken in spicy tomato sauce", 780.00, imageResourceId = R.drawable.chicken_tikka_masala),
            MenuItem("Chole Bhature", "Chickpea curry with deep-fried bread", 340.00, imageResourceId = R.drawable.chole_bhature),
        ),
        "Desserts" to listOf(
            MenuItem("Gulab Jamun", "Deep-fried milk balls in sugar syrup", 220.00, imageResourceId = R.drawable.gulab_jamun),
            MenuItem("Rasmalai", "Sweet cottage cheese in saffron milk", 280.00, imageResourceId = R.drawable.ras_malai),
            MenuItem("Jalebi", "Deep-fried sweet spirals", 200.00, imageResourceId = R.drawable.jalebi),
            MenuItem("Kulfi", "Indian ice cream", 250.00, imageResourceId = R.drawable.kulfi),
            MenuItem("Gajar Ka Halwa", "Carrot pudding", 280.00, imageResourceId = R.drawable.gajar_ka_halwa),
        ),
        "Beverages" to listOf(
            MenuItem("Masala Chai", "Spiced tea", 80.00, imageResourceId = R.drawable.masala_chai),
            MenuItem("Lassi", "Yogurt-based drink", 120.00, imageResourceId = R.drawable.lassi),
            MenuItem("Mango Shake", "Refreshing mango smoothie", 150.00, imageResourceId = R.drawable.mango_shake),
            MenuItem("Thandai", "Traditional Indian drink", 140.00, imageResourceId = R.drawable.thandai),
        ),
        "Sides" to listOf(
            MenuItem("Naan", "Leavened flatbread", 60.00, imageResourceId = R.drawable.naan),
            MenuItem("Jeera Rice", "Cumin-flavored rice", 90.00, imageResourceId = R.drawable.jeera_rice),
            MenuItem("Papad", "Crispy lentil wafers", 40.00, imageResourceId = R.drawable.papad),
            MenuItem("Raita", "Yogurt with herbs and spices", 80.00, imageResourceId = R.drawable.raita),
        )
    )
    return menuItems
}
