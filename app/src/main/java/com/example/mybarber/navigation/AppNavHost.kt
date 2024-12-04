package com.example.mybarber.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mybarber.ui.theme.screens.clientreservation.ClientReservationScreen
import com.example.mybarber.ui.theme.screens.dashboard.Dashboard
import com.example.mybarber.ui.theme.screens.login.LoginScreen
import com.example.mybarber.ui.theme.screens.signup.SignupScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),
               startDestination:String = ROUTE_LOGIN){
    NavHost(navController = navController,
        startDestination = startDestination ){
        composable(ROUTE_REGISTER){ SignupScreen(navController) }
        composable(ROUTE_LOGIN){ LoginScreen(navController)}
        composable(ROUTE_CLIENT_RESERVATION){ ClientReservationScreen(navController) }
        composable(ROUTE_HOME_PAGE){ Dashboard(navController) }


    }
}