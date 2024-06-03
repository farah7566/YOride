package com.example.yoride.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.yoride.R
import com.example.yoride.model.Car
import com.example.yoride.navigation.BMW_SCREEN
import com.example.yoride.navigation.BOOKNOW_SCREEN
import com.example.yoride.navigation.PROFILE_SCREEN
import com.example.yoride.ui.theme.YOrideTheme

@Composable
fun DetailScreen(carId: String?, navController: NavHostController) {
    data class CarDescription(
        val carId: String,
        val description: String
    )
    val carList = listOf(
        Car("1", "Lamborghini", "Lamborghini Aventador", 4.5, "800TND", R.drawable.lambo1),
        Car("2", "Tesla", "Model S", 4.8, "2500TND", R.drawable.tesla),
        Car("3", "Audi", "A4", 4.3, "900TND", R.drawable.audia4),
        Car("4", "Nissan", "Altima", 4.0, "700TND", R.drawable.pic_porche),
        Car("5", "Toyota", "Camry", 4.2, "900TND", R.drawable.camry),
        Car(id="6", "Ford", "1969 Ford Mustang", 5.0, "800TND", R.drawable.ford69)
    )
    val carDescriptions = mapOf(
        "1" to stringResource(id = R.string.car_description_1),
        "2" to stringResource(id = R.string.car_description_2),
        "3" to stringResource(id = R.string.car_description_3),
        "4" to stringResource(id = R.string.car_description_4),
        "5" to stringResource(id = R.string.car_description_5),
        "6" to stringResource(id = R.string.car_description_6)
    )

    val car = carList.find { it.id == carId } ?: return
    val carDescription = carDescriptions[carId] ?: "No description available."

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.mapsc),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
            Card(
                modifier = Modifier
                    .padding(top = 250.dp)
                    .fillMaxSize()
                    .border(
                        1.dp,
                        Color.Black,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                ) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = car.imageResId),
                                    contentDescription = car.model,
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier
                                        .scale(2f)
                                        .size(200.dp).align(Alignment.CenterHorizontally)
                                )
                                Text(
                                    text = car.model,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontSize = 23.sp,
                                    modifier = Modifier.padding(vertical = 8.dp))
                                Text(
                                    text = carDescription,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            }
                        }
                    }

                    item {
                        DriverInfo()
                    }
                    item {
                        AdressInfo()
                    }
                    item {
                        ButtonInfo(navController = navController)                    }
                }
            }
        }
    }
}

@Composable
fun DriverInfo() {
    Text(
        text = "",
        style = TextStyle(
            fontSize = 25.sp,
            color = Color.Black,

            fontWeight = FontWeight.W600
        ),
        modifier = Modifier.padding(horizontal = 20.dp)
    )
    Row(
        modifier = Modifier
            .padding(all = 12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile picture",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ){
            Text(
                text = "Renter Name",
                color = Color.DarkGray,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Renter",
                color = Color.LightGray,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_phone),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        thickness = 1.dp,
        color = Color.LightGray
    )
}
@Composable
fun AdressInfo() {
    var expanded by remember { mutableStateOf(false) }
    var selectedCity by remember { mutableStateOf("Tunis, Tunisia") }

    val cities = listOf("Tunis", "Ben Arous", "Ariana")

    Box(
        modifier = Modifier
            .padding(all = 1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = selectedCity,
                color = Color.DarkGray,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = city,
                        onClick = {
                            selectedCity = city
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DropdownMenuItem(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 50.dp, vertical = 7.dp)
    ) {
        Text(
            text = text,
          //  style = MaterialTheme.typography.body1
        )
    }
}
@Composable
fun ButtonInfo(navController: NavHostController) {
    Button(
        onClick = {  navController.navigate(BOOKNOW_SCREEN) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .height(50.dp)
    ) {
        Text(text = "Book Now", color = Color.White)
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YOrideTheme {
        DetailScreen( carId = "5",navController = rememberNavController())
    }
}