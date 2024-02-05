package uz.itschool.geo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.geo.R

@Composable
fun TestScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        TopBar(message = "Test", coins = 7, navController = navController)

        Image(painter = painterResource(id = R.drawable.red_flag), contentDescription = null)



        val options = mutableListOf<String>("qwer", "dfgh", "zxcv", "bxcvx")

        Box(modifier = Modifier.fillMaxSize().padding(16.dp)){

            LazyVerticalGrid(columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.BottomCenter)){

                items(options){option ->

                    OptionItem(text = option)

                }
            }
        }




        

    }
}

@Composable
fun OptionItem(text: String){

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = text,
            fontSize = 20.sp)
    }

}






@Preview(showBackground = true)
@Composable
fun testtest(){
    val navController = rememberNavController()
    TestScreen(navController)
}