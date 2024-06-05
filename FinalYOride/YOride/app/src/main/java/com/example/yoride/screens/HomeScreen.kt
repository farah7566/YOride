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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.yoride.R
import com.example.yoride.model.Car
import com.example.yoride.navigation.BMW_SCREEN
import com.example.yoride.navigation.DETAIL_SCREEN
import com.example.yoride.navigation.PROFILE_SCREEN
import com.example.yoride.navigation.TESLA_SCREEN
import com.example.yoride.ui.theme.DarkGray
import com.example.yoride.ui.theme.Gray
import com.example.yoride.ui.theme.YOrideTheme
import com.example.yoride.ui.theme.Background
import fingerfire.com.aluguecarro.common.AppIcon

@Composable
fun HomeScreen(navHostController: NavHostController) {
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
                        text = "Any Road. Any Ride. YoDrive",
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.Black,
                            //fontFamily = BebasFont,
                            fontWeight = FontWeight.W600
                        ),
                        modifier = Modifier.padding(vertical = 30.dp)
                    )

                    Box {
                        CustomSearchBar(value = search, onValueChange = { search = it })
                        AppIcon(
                            icon = R.drawable.ic_filter,
                            background = Color.Black,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(55.dp)
                        )
                    }

                    val menuList = listOf("BMW", "Tesla", "Audi", "Nissan", "Toyota")
                    val menuListWithImages = mapOf(
                        "BMW" to painterResource(R.drawable.icon_bmw),
                        "Tesla" to painterResource(R.drawable.icon_tesla),
                        "Audi" to painterResource(R.drawable.icon_audi),
                        "Nissan" to painterResource(R.drawable.icon_nissan),
                        "Toyota" to painterResource(R.drawable.icon_toyota)
                    )

                    var selectedIndex by remember { mutableStateOf(-1) }

                    val carList = listOf(
                        Car(
                            id="1",
                            "Lamborghini",
                            "Lamborghini Aventador",
                            4.5,
                            "800TND/day",
                            R.drawable.lambo1
                        ),

                        Car(                            id="2",

                            "Tesla",
                            "Tesla",
                            4.8,
                            "500TND/day",
                            R.drawable.tesla
                        ),
                        Car(                            id="3",

                            "Audi",
                            "A4",
                            4.3,
                            "900TND/day",
                            R.drawable.audia4
                        ),
                        Car(                            id="4",

                            "Nissan",
                            "Altima",
                            4.0,
                            "200TND/day",
                            R.drawable.nissan
                        ),
                        Car(                            id="5",

                            "Toyota",
                            "Camry",
                            4.2,
                            "250TND/day",
                            R.drawable.camry
                        ),
                        Car(
                            id="6",
                            "Ford",
                            "1969 Ford Mustang",
                            5.0,
                            "800TND/day",
                            R.drawable.ford69
                        ),
                        Car(
                            id="7",
                            "Tesla",
                            "Tesla model X",
                            4.5,
                            "300TND",
                            R.drawable.modelx
                        ),

                        Car(                            id="8",

                            "Tesla",
                            "Tesla model Y",
                            4.8,
                            "250TND",
                            R.drawable.modely
                        ),
                    )

                    LazyRow(modifier = Modifier.padding(vertical = 20.dp)) {
                        itemsIndexed(menuList) { index, menuItem ->
                            val isSelected = index == selectedIndex
                            Card(
                                shape = RoundedCornerShape(22.dp),
                                modifier = Modifier
                                    .padding(end = 15.dp)
                                    .clickable { selectedIndex = index
                                        if (menuItem == "BMW") {
                                            navHostController.navigate(BMW_SCREEN);
                                        } else if (menuItem == "Tesla") {
                                            navHostController.navigate(TESLA_SCREEN); // Assuming TESLA_SCREEN is defined elsewhere
                                        }}
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(
                                        vertical = 10.dp,
                                        horizontal = 20.dp
                                    )
                                ) {
                                    if (isSelected) {
                                        Box(
                                            modifier = Modifier
                                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                                        )
                                    }

                                    Column(modifier = Modifier
                                        .padding(vertical = 20.dp)
                                        ,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = menuListWithImages[menuItem]
                                                ?: painterResource(R.drawable.ic_profile_bl),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(bottom = 8.dp)
                                        )
                                        Text(
                                            text = menuItem,
                                            style = TextStyle(
                                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                                                fontWeight = FontWeight.W400,
                                                fontSize = 18.sp,
                                                fontFamily = FontFamily.Default
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                    val filteredCarList = carList.filter { it.brand.contains(search, ignoreCase = true) || it.model.contains(search, ignoreCase = true) }

                    LazyColumn {
                        itemsIndexed(filteredCarList) { _, car ->
                            CarItem(car = car, navHostController)
                        }
                    }
                    }
                }
            }
        }
    }




@Composable
fun CarItem(car: Car, navHostController: NavHostController) {
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
            CarImage(imageResId = car.imageResId)
            Spacer(modifier = Modifier.width(16.dp))
            CarInfo(car = car)
        }
    }
}

@Composable
fun CarImage(imageResId: Int) {
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
fun CarInfo(car: Car) {
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
                fontSize = 16.sp
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.5.dp))
            .background(Gray)
    ) {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = "Find your car",
                    style = TextStyle(
                        fontSize = 16.sp,
                        //fontFamily = BebasFont,
                        fontWeight = FontWeight.W400,
                        color = DarkGray
                    )
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                //backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            trailingIcon = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navHostController = rememberNavController()
    HomeScreen(navHostController = navHostController)
}
