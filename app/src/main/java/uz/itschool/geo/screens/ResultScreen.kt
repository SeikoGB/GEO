package uz.itschool.geo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            fontSize = 60.sp,
            //modifier = Modifier.align(Alignment.Center)
            )

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                navController.navigate(Screens.Home.route)
            }) {
                Icon(imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.White)
            }

            Button(onClick = {
                navController
                .navigate(Screens.Level
                    .passCategoryType(viewModel
                        .thisLevel.categoryName)) }) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.White)
            }

            Button(onClick = {
                navController.navigate(Screens.Test
                    .passLevelId(id = viewModel.thisLevel.id)) }) {
                Icon(imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun resulttest(){
    val navController = rememberNavController()
    val viewModel = TestViewModel(0)
    ResultScreen(navController, viewModel)
}