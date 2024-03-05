package uz.itschool.geo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.geo.navigation.Screens
import uz.itschool.geo.screens.TestScreen.TestViewModel
import uz.itschool.geo.ui.theme.myLightBlue
import uz.itschool.geo.ui.theme.myRed

@Composable
fun ResultScreen(navController: NavController,
                 viewModel: TestViewModel){

    //TopBar(message = , coins = , navController = )

    val isWon = viewModel.isWon.observeAsState().value!!

    var wonMessage by remember {
        mutableStateOf("")
    }

    var resultBG by remember {
        mutableStateOf(myRed)
    }

    if (isWon){
        wonMessage = "You Won"
        resultBG = myLightBlue
    }else{
        wonMessage = "You lost"
        resultBG = myRed
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(resultBG),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround){
        Text(text = wonMessage,
            fontSize = 40.sp,
            //modifier = Modifier.align(Alignment.Center)
            )

        Button(onClick = { navController.navigate(Screens.Home.route) }) {
            Text(text = "Home")

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun resulttest(){
    val navController = rememberNavController()
    val viewModel = TestViewModel("levelName")
    ResultScreen(navController, viewModel)
}