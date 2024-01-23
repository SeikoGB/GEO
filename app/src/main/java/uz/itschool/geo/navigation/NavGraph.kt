package uz.itschool.geo.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.itschool.geo.screens.HomeScreen
import uz.itschool.geo.screens.SplashScreen


@Composable
fun NavGraph(navHostController: NavHostController){

    NavHost(navController = navHostController,
        startDestination = Screens.Splash.route,
    ){
        composable(route = Screens.Splash.route){
            SplashScreen(navHostController)
        }
        composable(route = Screens.Home.route){
            HomeScreen()
        }

    }
}