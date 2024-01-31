package uz.itschool.geo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.geo.R

@Composable
fun TestScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        TopBar(message = "Test", coins = 7, navController = navController)

        Image(painter = painterResource(id = R.drawable.red_flag), contentDescription = null)


        

    }
}






@Preview(showBackground = true)
@Composable
fun testtest(){
    val navController = rememberNavController()
    TestScreen(navController)
}