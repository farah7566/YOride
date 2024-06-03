package com.example.yoride.screens

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.yoride.R
import com.example.yoride.navigation.HOME_SCREEN
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

data class UserProfile(
    val name: String,
    val birth: String,
    val email: String,
    val tel: String,
    val pass: String,
    val profileImageRes: Int)
fun saveUserProfile(userProfile: UserProfile) {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("userProfiles")

    val userKey = userProfile.email.replace(".", ",")
    myRef.child(userKey).setValue(userProfile)
        .addOnSuccessListener {
            Log.d("Firebase", "User profile saved successfully")
        }
        .addOnFailureListener {
            Log.d("Firebase", "Failed to save user profile")
        }
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("Enter name") }
    var birth by remember { mutableStateOf("Enter Birthday") }
    var tel by remember { mutableStateOf("Enter number") }
    var email by remember { mutableStateOf("Enter Email") }
    var pass by remember { mutableStateOf("Password") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val isFormValid = name.isNotBlank() && birth.isNotBlank() && tel.isNotBlank() && email.isNotBlank() && pass.isNotBlank()

    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .size(58.dp)
                .align(Alignment.TopCenter),
            tint = Color.Unspecified
        )
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            item {
                Text(
                    text = "Create Account/Login",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))

                ProfileTextField(value = name, onValueChange = { name = it }, label = "Name", icon = Icons.Default.Person)
                ProfileTextField(value = birth, onValueChange = { birth = it }, label = "Birthday", icon = Icons.Default.DateRange)
                ProfileTextField(value = tel, onValueChange = { tel = it }, label = "Phone Number", icon = Icons.Default.Phone)
                ProfileTextField(value = email, onValueChange = { email = it }, label = "Email", icon = Icons.Default.Email)
                ProfileTextField(value = pass, onValueChange = { pass = it }, label = "Password", icon = Icons.Default.Check, isPassword = true)

                Spacer(modifier = Modifier.height(26.dp))

                Button(
                    onClick = {
                        val userProfile = UserProfile(
                            name = name,
                            birth = birth,
                            tel = tel,
                            email = email,
                            pass = pass,
                            profileImageRes = R.drawable.profile
                        )
                        saveUserProfile(userProfile)
                        scope.launch {
                            snackbarHostState.showSnackbar("User saved successfully")
                        }
                        navController.navigate(HOME_SCREEN)
                    },
                    modifier = Modifier.height(50.dp),
                    enabled = isFormValid
                ) {
                    Text("Save Profile")
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions.Default.copy(imeAction = ImeAction.Done) else KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle IME action here if needed
            }
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}