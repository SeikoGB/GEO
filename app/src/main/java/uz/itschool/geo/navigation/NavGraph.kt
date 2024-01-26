package uz.itschool.geo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.itschool.geo.screens.HomeScreen
import uz.itschool.geo.screens.LevelsScreen
import uz.itschool.geo.screens.SplashScreen


@Composable
fun NavGraph(navController: NavHostController){

    NavHost(navController = navController,
        startDestination = Screens.Splash.route,
    ){
        composable(route = Screens.Splash.route){
            SplashScreen(navController)
        }
        composable(route = Screens.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screens.Level.route){
            LevelsScreen(navController)
        }
    }
}