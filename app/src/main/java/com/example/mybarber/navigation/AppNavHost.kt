package com.example.mybarber.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mybarber.ui.theme.screens.SplashScreen
import com.example.mybarber.ui.theme.screens.clientreservation.ClientReservationScreen
import com.example.mybarber.ui.theme.screens.clientreservation.UpdateReservationScreen
import com.example.mybarber.ui.theme.screens.clientreservation.ViewClients
import com.example.mybarber.ui.theme.screens.dashboard.Dashboard
import com.example.mybarber.ui.theme.screens.login.LoginScreen
import com.example.mybarber.ui.theme.screens.signup.SignupScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),
               startDestination:String = ROUTE_SPLASH){
    NavHost(navController = navController,
        startDestination = startDestination ){
        composable(ROUTE_SPLASH) { SplashScreen {
            navController.navigate(ROUTE_LOGIN){
                popUpTo(ROUTE_SPLASH){inclusive = true}
            }
        }  }
        composable(ROUTE_REGISTER){ SignupScreen(navController) }
        composable(ROUTE_LOGIN){ LoginScreen(navController)}
        composable(ROUTE_CLIENT_RESERVATION){ ClientReservationScreen(navController) }
        composable(ROUTE_HOME_PAGE){ Dashboard(navController) }
        composable(ROUTE_ADD_CLIENT){ ClientReservationScreen(navController) }
        composable(ROUTE_VIEW_CLIENT){ ViewClients(navController) }
        composable("$ROUTE_UPDATE_CLIENT/{id}") {
                passedData -> UpdateReservationScreen(
            navController, passedData.arguments?.getString("id")!! )
        }


    }
}