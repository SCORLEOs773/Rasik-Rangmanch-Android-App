package com.example.rasikrangmanch

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    val samaranFamily = FontFamily(Font(R.font.samaran))
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.primary
        color = Color(0xFFFF9933)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
            Text(
                text = "Rasik Rangmanch",
                fontSize = 50.sp,
                color = Color(0xFFA52A2A),
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 4.dp),
                fontFamily = samaranFamily
            )
//            Text(
//                text = "LOGIN",
//                style = MaterialTheme.typography.titleLarge,
//                color = Color.White,
////                modifier = Modifier.size(24.dp)
//            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            ElevatedButton(
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color(0xFFFF5733),
                    contentColor = Color.White
                ),
                onClick = {
                    navController.navigate(Home.route)
                    Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login", fontSize = 18.sp)
            }
        }
    }
}