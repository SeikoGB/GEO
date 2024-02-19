package uz.itschool.geo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.itschool.geo.screens.HomeScreen
import uz.itschool.geo.screens.learnScreen.LearnScreen
import uz.itschool.geo.screens.levelScreen.LevelsScreen
import uz.itschool.geo.screens.SplashScreen
import uz.itschool.geo.screens.TestScreen.TestScreen
import uz.itschool.geo.screens.TestScreen.TestViewModel
import uz.itschool.geo.screens.learnScreen.LearnViewModel
import uz.itschool.geo.screens.levelScreen.LevelViewModel

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
                val levelViewModel = LevelViewModel(categoryName)
                LevelsScreen(
                    navController = navController,
                    viewModel = levelViewModel)
            }
        }

        composable(route = Screens.Test.route,
            arguments = listOf(navArgument(PASS_LEVEL_TYPE){
                type = NavType.StringType
            })
        ){navBackStackEntry ->
            val levelName = navBackStackEntry.arguments?.getString(PASS_LEVEL_TYPE)

            if (levelName != null){
                val viewModel = TestViewModel(levelName)
                viewModel.startTime()
                viewModel.randomiseList()
                TestScreen(
                    navController = navController,
                    viewModel = viewModel)
            }

        }
        
        composable(route = Screens.Learn.route){
            val learnViewModel = LearnViewModel(LocalContext.current)
            LearnScreen(navController = navController, viewModel = learnViewModel)
        }
    }
}

