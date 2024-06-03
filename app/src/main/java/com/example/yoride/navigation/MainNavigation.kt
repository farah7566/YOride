package com.example.yoride.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yoride.screens.BmwScreen
import com.example.yoride.screens.BookingScreen
import com.example.yoride.screens.DetailScreen
import com.example.yoride.screens.HomeScreen
import com.example.yoride.screens.PaymentDetailsScreen
import com.example.yoride.screens.ProfileScreen
import com.example.yoride.screens.StartScreen
import com.example.yoride.screens.ThankYouScreen

@Composable
fun MainNavigation() {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = SPLASH_SCREEN) {
        composable(SPLASH_SCREEN) {
            StartScreen(navHostController)
        }
        composable(HOME_SCREEN) {
            HomeScreen(navHostController)
        }
        composable("$DETAIL_SCREEN/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId")

            DetailScreen(carId = carId,navHostController)
        }
        composable(PROFILE_SCREEN) {
            ProfileScreen(navHostController)
        }
        composable(BOOKNOW_SCREEN) {
            BookingScreen(navHostController)
        }
        composable(BMW_SCREEN) {
            BmwScreen(navHostController)
        }
        composable(THANKYOU_SCREEN) {
            ThankYouScreen(navHostController)
        }
        composable(CARD_SCREEN) {
            PaymentDetailsScreen(navHostController)
        }

    }
}


const val SPLASH_SCREEN = "splash_screen"
const val HOME_SCREEN = "home_screen"
const val DETAIL_SCREEN = "detail_screen"
const val PROFILE_SCREEN="profile_screen"
const val BMW_SCREEN="bmw_screen"
const val BOOKNOW_SCREEN="booknow_screen"
const val THANKYOU_SCREEN="thankyou_screen"
const val CARD_SCREEN="card_screen"