package com.example.yoride.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.yoride.R
import com.example.yoride.model.Car
import com.example.yoride.navigation.DETAIL_SCREEN
import com.example.yoride.navigation.PROFILE_SCREEN
import com.example.yoride.ui.theme.Background
import com.example.yoride.ui.theme.YOrideTheme
import fingerfire.com.aluguecarro.common.AppIcon

@Composable
fun BmwScreen(navHostController: NavHostController) {
    YOrideTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            var search by remember { mutableStateOf("") }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AppIcon(icon = R.drawable.ic_menu)
                        Icon(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "",
                            modifier = Modifier.size(58.dp),
                            tint = Color.Unspecified
                        )
                        AppIcon(
                            icon = R.drawable.ic_profile_bl,
                            onClick = { navHostController.navigate(PROFILE_SCREEN) }
                        )
                    }

                    Text(
                        text = "Browse Our Exclusive BMW Lineup",
                        style = TextStyle(
                            fontSize = 22.sp,
                            color = Color.Black,
                            //fontFamily = BebasFont,
                            fontWeight = FontWeight.W600
                        ),
                        modifier = Modifier.padding(vertical = 30.dp)
                    )

                    var selectedIndex by remember { mutableStateOf(-1) }

                    val carList = listOf(
                        Car(
                            id="1",
                            "BMW",
                            "BMW 2 Series Gran Coupé",
                            4.5,
                            "800TND",
                            R.drawable.bmw1
                        ),

                        Car(                            id="2",

                            "BMW",
                            "BMW X2 sDrive20i M Sport",
                            4.8,
                            "1210TND",
                            R.drawable.bmw2
                        ),
                        Car(                            id="3",

                            "BMW",
                            "THE BMW 4 Series Convertible M",
                            4.3,
                            "1000TND",
                            R.drawable.bmw5
                        ),
                        Car(                            id="4",

                            "BMW",
                            "BMW M3 Competition M xDrive Saloon",
                            4.0,
                            "700TND",
                            R.drawable.bmw4
                        ),
                        Car(                            id="5",

                            "BMW",
                            "BMW M440i xDrive Gran Coupé",
                            4.2,
                            "1500TND",
                            R.drawable.bmw5
                        ),

                        )

                    LazyColumn {
                        itemsIndexed(carList) { _, car ->
                            CarItem(car = car, navHostController)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun BmwItem(car: Car, navHostController: NavHostController) {
    Card(
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .clickable {
                navHostController.navigate("$DETAIL_SCREEN/${car.id}")
            }
    ) {
        Row(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
        ) {
            BmwImage(imageResId = car.imageResId)
            Spacer(modifier = Modifier.width(16.dp))
            BmwInfo(car = car)
        }
    }
}

@Composable
fun BmwImage(imageResId: Int) {
    Image(
        painter = painterResource(imageResId),
        contentScale = ContentScale.FillWidth,
        contentDescription = null,
        modifier = Modifier
            .height(120.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun BmwInfo(car: Car) {
    Column {
        Text(
            text = car.brand,
            color = Color.Black,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(
            modifier = Modifier.height(4.dp)
        )
        Text(
            text = car.model,
            color = Color.Gray,
            style = TextStyle(
                fontSize = 14.sp
            )
        )
        Spacer(
            modifier = Modifier.height(4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            Image(
                painterResource(
                    id = R.drawable.ic_star
                ), contentDescription = null
            )
            Text(
                text = "${car.rating}",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
        }
        Spacer(
            modifier = Modifier.height(4.dp)
        )
        Row{
            Text(
                text = car.rentalPrice,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BmwScreenPreview() {
    val navHostController = rememberNavController()
    BmwScreen(navHostController = navHostController)
}