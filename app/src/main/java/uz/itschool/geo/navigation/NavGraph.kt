package uz.itschool.geo.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.itschool.geo.model.CategoryType
import uz.itschool.geo.screens.HomeScreen
import uz.itschool.geo.screens.learnScreen.LearnScreen
import uz.itschool.geo.screens.levelScreen.LevelsScreen
import uz.itschool.geo.screens.SplashScreen
import uz.itschool.geo.screens.TestScreen.CapitalCityTestScreen
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
            val categoryName = navBackStackEntry.arguments?.getString(PASS_CATEGORY_TYPE)




            Log.d("nav", "NavGraph: ${levelName}")



            if (levelName != null){
                val l = levelName.drop(1).dropLast(1)
                Log.d("re", "NavGraph: ${levelName}")

                val viewModel = TestViewModel(l)
                viewModel.startTime()
                //viewModel.randomiseList()
                Log.d("TAggG", "NavGraph: ${viewModel.countries.value!!}")

                when(categoryName){
                    "{${CategoryType.BY_FLAG.text}}" ->{
                        TestScreen(
                            navController = navController,
                            viewModel = viewModel)

                        Log.d("TAG", "NavGraph: flag ish")

                    }
                    "{${CategoryType.BY_CAPITAL.text}}" ->{
                        CapitalCityTestScreen()
                    }
                }

            }

        }
        
        composable(route = Screens.Learn.route){
            val learnViewModel = LearnViewModel()
            LearnScreen(navController = navController, viewModel = learnViewModel)
        }
    }
}

