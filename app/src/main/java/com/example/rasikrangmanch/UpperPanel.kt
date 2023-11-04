package com.example.rasikrangmanch

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
//@Preview
fun UpperPanel(navController: NavController)
{
    val context = LocalContext.current
    val searchPhrase = remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF9933))
    ) {
        Row {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "",
                modifier = Modifier.height(70.dp).width(70.dp).padding(end = 8.dp))
            Text(
                text = "Rasik Rangmanch",
                fontSize = 32.sp,
                color = Color(0xFFA52A2A),
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
//        Text(
//            text = stringResource(id = R.string.chandigarh),
//            fontSize = 24.sp,
//            color = Color(0xFFFFFFFF),
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.descriptionone),
                Modifier
                    .width(200.dp)
                    .padding(top = 20.dp),
                color = Color.White,
                fontSize = 21.sp
            )

            Image(painter = painterResource(id = R.drawable.img1),
                contentDescription = "",
                Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .padding(top = 20.dp, start = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }

        Button(
            onClick = { navController.navigate(Menu.route)
                Toast.makeText(context, "Opening The Menu!", Toast.LENGTH_SHORT).show()
                      },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        )
        {
            Text(
                text = stringResource(id = R.string.order),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}