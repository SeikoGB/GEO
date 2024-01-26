package uz.itschool.geo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import uz.itschool.geo.navigation.Screens

@Composable
fun SplashScreen(navHostController: NavController){

    LaunchedEffect(key1 = true){
        delay(1500)
        navHostController.navigate(Screens.Home.route)
    }

    Box(modifier = Modifier.fillMaxSize())

}


@Preview(showBackground = true)
@Composable
fun test(){
    val navController = rememberNavController()
    SplashScreen(navController)
}