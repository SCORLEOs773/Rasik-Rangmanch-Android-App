package com.example.rasikrangmanch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round

@Composable
@Preview
fun FeaturedPanel() {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
//            .background(Color(0xFFFAEBD7)),
            .background(Color(0xFFFFD7A2)),
        contentPadding = PaddingValues(16.dp),
    ) {
        item {
            Text(
                text = "Chef's Signature Dishes",
                fontSize = 24.sp,
                color = Color(0xFF800000),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
        items(count = 1)
        {countValue ->
            FeaturedDish("Biryani", R.drawable.biryani, 4.5f)
            Spacer(modifier = Modifier.height(12.dp))
            FeaturedDish("Butter Chicken", R.drawable.butter_chicken, 4.2f)
            Spacer(modifier = Modifier.height(12.dp))
            FeaturedDish("Paneer Tikka", R.drawable.paneer_tikka, 4.0f)
            Spacer(modifier = Modifier.height(12.dp))
            FeaturedDish("Tandoori Chicken", R.drawable.tandoori_chicekn, 4.0f)
        }
    }
}

@Composable
fun FeaturedDish(name: String, imageRes: Int, rating: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Good",
            modifier = Modifier
                .size(120.dp).padding(start = 10.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.padding(start = 20.dp)
        ){
            Text(text = name, fontSize = 21.sp, color = Color.Black, modifier = Modifier.padding(bottom = 4.dp), textAlign = TextAlign.Center)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Rating:", fontSize = 16.sp, color = Color(0xFFA9A9A9))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "$rating ⭐",
                    fontSize = 16.sp,
                    color = Color(0xFFA9A9A9))
            }
        }
    }
}