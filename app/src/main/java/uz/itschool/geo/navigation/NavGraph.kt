package uz.itschool.geo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.itschool.geo.screens.HomeScreen
import uz.itschool.geo.screens.LearnScreen
import uz.itschool.geo.screens.LevelsScreen
import uz.itschool.geo.screens.SplashScreen
import uz.itschool.geo.screens.TestScreen

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

        composable(
            route = Screens.Level.route,
            arguments = listOf(navArgument(PASS_CATEGORY_TYPE){
                type = NavType.StringType
            })
        ){navBackStackEntry ->
            val categoryName =navBackStackEntry.arguments?.getString(PASS_CATEGORY_TYPE)
            if (categoryName != null){
                LevelsScreen(
                    navController = navController,
                    categoryName = categoryName.toString())
            }
        }

        composable(route = Screens.Test.route){
            TestScreen(navController = navController)
        }
        
        composable(route = Screens.Learn.route){
            LearnScreen(navController = navController)
        }
    }
}

