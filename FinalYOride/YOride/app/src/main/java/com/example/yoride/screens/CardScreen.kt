package com.example.yoride.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.yoride.R
import com.example.yoride.navigation.THANKYOU_SCREEN
import com.google.firebase.database.FirebaseDatabase

data class PaymentDetails(
    val cardholderName: String,
    val cardNumber: String,
    val expiryDate: String,
    val securityCode: String,
    val billingAddress: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentDetailsScreen(navController: NavHostController) {
    var cardholderName by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var securityCode by remember { mutableStateOf("") }
    var billingAddress by remember { mutableStateOf("") }

    val isFormValid = cardholderName.isNotBlank() && cardNumber.isNotBlank() && expiryDate.isNotBlank() && securityCode.isNotBlank() && billingAddress.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Payment details", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .background(Color.White)
            ) {

                Text(text = "Accepted cards:", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    CardTypeImage()
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = cardholderName,
                    onValueChange = { cardholderName = it },
                    label = { Text("Cardholder name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = { Text("Card number") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = expiryDate,
                    onValueChange = { expiryDate = it },
                    label = { Text("Expiry date (MM/YY)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = securityCode,
                    onValueChange = { securityCode = it },
                    label = { Text("Security code") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = billingAddress,
                    onValueChange = { billingAddress = it },
                    label = { Text("Billing address") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val paymentDetails = PaymentDetails(
                            cardholderName = cardholderName,
                            cardNumber = cardNumber,
                            expiryDate = expiryDate,
                            securityCode = securityCode,
                            billingAddress = billingAddress
                        )
                        savePaymentDetails(paymentDetails)
                        navController.navigate(THANKYOU_SCREEN)
                    },
                    enabled = isFormValid,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Pay Now")
                }
            }
        }
    )
}

@Composable
fun CardTypeImage() {
    Image(
        painter = painterResource(id = R.drawable.pay),
        contentDescription = "Accepted Card Type",
        modifier = Modifier.size(80.dp)
    )
}

fun savePaymentDetails(paymentDetails: PaymentDetails) {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("PaymentDetails")

    val paymentKey = myRef.push().key ?: return
    myRef.child(paymentKey).setValue(paymentDetails)
        .addOnSuccessListener {
            Log.d("Firebase", "Payment details saved successfully")
        }
        .addOnFailureListener {
            Log.d("Firebase", "Failed to save payment details")
        }
}

@Preview(showBackground = true)
@Composable
fun PaymentDetailsScreenPreview() {
    PaymentDetailsScreen(navController = rememberNavController())
}
