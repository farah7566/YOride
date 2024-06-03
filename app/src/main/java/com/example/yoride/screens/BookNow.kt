package com.example.yoride.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.yoride.R
import com.example.yoride.navigation.CARD_SCREEN
import com.example.yoride.navigation.THANKYOU_SCREEN
import com.google.firebase.database.FirebaseDatabase
@Composable
fun BookingScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var nbdays by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) }
    var payByCard by remember { mutableStateOf(false) }
    var payOnPickup by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val isFormValid = username.isNotBlank() && date.isNotBlank() && nbdays.isNotBlank() && place.isNotBlank() && agreedToTerms

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier.size(58.dp),
            tint = Color.Unspecified
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { /* Handle next action */ }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { /* Handle next action */ }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nbdays,
            onValueChange = { nbdays = it },
            label = { Text("Number of Days") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = place,
            onValueChange = { place = it },
            label = { Text("Place") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it },
            )
            Text("Agree to Terms and Conditions")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    val nbdaysInt = nbdays.toIntOrNull() ?: 1 // Default to 1 day if conversion fails
                    saveBookingToFirebase(username, date, place, nbdaysInt, agreedToTerms, payByCard, payOnPickup)
                    navController.navigate(THANKYOU_SCREEN)
                },
                enabled = isFormValid,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text("Pay on Pickup")
            }
            Button(
                onClick = {
                    val nbdaysInt = nbdays.toIntOrNull() ?: 1 // Default to 1 day if conversion fails
                    saveBookingToFirebase(username, date, place, nbdaysInt, agreedToTerms, payByCard, payOnPickup)
                    navController.navigate(CARD_SCREEN)
                },
                enabled = isFormValid,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text("Pay by Card")
            }
        }
    }
}

fun saveBookingToFirebase(username: String, date: String, place: String, nbdays: Int, agreedToTerms: Boolean, payByCard: Boolean, payOnPickup: Boolean) {
    val database = FirebaseDatabase.getInstance().reference
    val bookingId = database.child("bookings").push().key
    val booking = Booking(username, date, place, nbdays, agreedToTerms, payByCard, payOnPickup)

    if (bookingId != null) {
        database.child("bookings").child(bookingId).setValue(booking)
    }
}

data class Booking(
    val username: String,
    val date: String,
    val place: String,
    val nbdays: Int,
    val agreedToTerms: Boolean,
    val payByCard: Boolean,
    val payOnPickup: Boolean
)

@Preview
@Composable
fun BookingScreenPreview() {
    BookingScreen(navController = rememberNavController())

}