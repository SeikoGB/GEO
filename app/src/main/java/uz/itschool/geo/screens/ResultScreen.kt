package uz.itschool.geo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import uz.itschool.geo.screens.TestScreen.TestViewModel
import uz.itschool.geo.screens.levelScreen.TopBar

@Composable
fun ResultScreen(navController: NavController,
                 viewModel: TestViewModel){

    //TopBar(message = , coins = , navController = )


    Box(modifier = Modifier.fillMaxSize()){
        Text(text = viewModel.levelName)
    }

}