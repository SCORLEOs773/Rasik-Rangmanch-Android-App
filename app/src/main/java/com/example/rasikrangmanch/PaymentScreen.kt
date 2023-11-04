package com.example.rasikrangmanch

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentScreen() {
    val context = LocalContext.current
    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var expirationDate by remember { mutableStateOf(TextFieldValue()) }
    var cvv by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Payment Information",
            modifier = Modifier.padding(start = 75.dp),
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(getDummyFoodItems()) { foodItem ->
                FoodItemRow(foodItem = foodItem)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Payment Details", fontSize = 14.sp)

        OutlinedTextField(
            value = cardNumber,
            onValueChange = {
                cardNumber = it
            },
            label = { Text("Card Number") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = expirationDate,
            onValueChange = {
                expirationDate = it
            },
            label = { Text("Expiration Date") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cvv,
            onValueChange = {
                cvv = it
            },
            label = { Text("CVV") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ElevatedButton(
            onClick = {Toast.makeText(context, "Payment Successful!", Toast.LENGTH_SHORT).show()},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFFFF8A23),
                contentColor = Color.White
            )
        ) {
            Text("Make Payment")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Payment Options")

        val paymentOptions = listOf("Credit Card", "Debit Card", "Net Banking", "UPI")
        var selectedOption by remember { mutableStateOf(paymentOptions[0]) }

        paymentOptions.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedOption = option
                    }
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { selectedOption = option }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total Amount:", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
            Text("₹${calculateTotalAmount(getDummyFoodItems())}", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Handle Payment */ },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Make Payment")
        }
    }
}

@Composable
fun FoodItemRow(foodItem: MenuItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = foodItem.name,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            modifier = Modifier.padding(end = 20.dp))
        Text(
            text = "Qty: ${foodItem.quantity} Price: ₹${foodItem.price * foodItem.quantity}",
            fontWeight = FontWeight.Medium
        )
    }
}

fun getDummyFoodItems(): List<MenuItem> {
    return listOf(
        MenuItem("Samosa", "", 190.0,1, 2),
        MenuItem("Butter Chicken", "", 750.0, 1, 1),
        MenuItem("Masala Chai", "", 80.0, 1, 3)
    )
}

fun calculateTotalAmount(foodItems: List<MenuItem>): Double {
    return foodItems.sumOf { it.price * it.quantity }
}