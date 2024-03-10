package uz.itschool.geo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.itschool.geo.model.CategoryType
import uz.itschool.geo.screens.HomeScreen
import uz.itschool.geo.screens.ResultScreen
import uz.itschool.geo.screens.learnScreen.LearnScreen
import uz.itschool.geo.screens.levelScreen.LevelsScreen
import uz.itschool.geo.screens.SplashScreen
import uz.itschool.geo.screens.TestScreen.ImgByTextTestScreen
import uz.itschool.geo.screens.TestScreen.TextByImgTestScreen
import uz.itschool.geo.screens.TestScreen.TestViewModel
import uz.itschool.geo.screens.TestScreen.TextByTextTestScreen
import uz.itschool.geo.screens.learnScreen.LearnViewModel
import uz.itschool.geo.screens.levelScreen.LevelViewModel

lateinit var testViewModel: TestViewModel

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

            if (levelName != null){
                var viewModelCreated by remember {
                    mutableStateOf(false)
                }

                if (!viewModelCreated){
                    testViewModel = TestViewModel(levelName.drop(1).dropLast(1))
                    testViewModel.startTimer()
                    viewModelCreated = true
                }

                when(categoryName){
                    "{${CategoryType.COUNTRY_BY_FLAG.path}}" ->{
                        testViewModel.questionType = "flag"
                        testViewModel.answerType = "country"
                        TextByImgTestScreen(
                            navController = navController,
                            viewModel = testViewModel)
                    }
                    "{${CategoryType.COUNTRY_BY_CAPITAL.path}}" ->{
                        testViewModel.questionType = "capital"
                        testViewModel.answerType = "country"
                        TextByTextTestScreen()
                    }
                    "{${CategoryType.FLAG_BY_COUNTRY.path}}" ->{
                        testViewModel.questionType = "country"
                        testViewModel.answerType = "flag"
                        ImgByTextTestScreen(
                            navController = navController,
                            viewModel = testViewModel)
                    }
                    "{${CategoryType.FLAG_BY_CAPITAL.path}}" ->{
                        testViewModel.questionType = "capital"
                        testViewModel.answerType = "flag"
                        ImgByTextTestScreen(
                            navController = navController,
                            viewModel = testViewModel)
                    }
                }
            }
        }
        
        composable(route = Screens.Learn.route){
            val learnViewModel = LearnViewModel()
            LearnScreen(navController = navController, viewModel = learnViewModel)
        }

        composable(route = Screens.Result.route){
            ResultScreen(navController = navController, viewModel = testViewModel)
        }
    }
}

