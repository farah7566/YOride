package com.example.yoride.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.yoride.R
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.yoride.navigation.HOME_SCREEN

@Composable
fun ThankYouScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier
       // Make the screen take the full height
            .padding(16.dp),
        contentAlignment = Alignment.Center  // Center everything within the Box
    ) {
        Column(modifier = Modifier
            .fillMaxSize().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(45.dp),
                tint = Color.Unspecified
            )
            Text(
                text = "Thank You for Your Order!",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 40.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.clap1),
                contentDescription = "",
                modifier = Modifier.size(170.dp),
                tint = Color.Unspecified
            )
            Text(
                text = "Your order will be ready for pickup at our location in Tunis.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Box(
                modifier = Modifier
                    .width(200.dp)  // Adjust the button width
                    .background(Color.Black)
                    .clickable {
                        navHostController.navigate(HOME_SCREEN)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Back to Home",
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThankYouScreenPreview() {
    val navController = rememberNavController()
    ThankYouScreen(navHostController = navController)
}
