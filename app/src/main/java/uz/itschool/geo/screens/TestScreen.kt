package uz.itschool.geo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.geo.R
import uz.itschool.geo.ui.theme.myBlue
import uz.itschool.geo.ui.theme.whiteBackround

@Composable
fun TestScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize().background(whiteBackround),
        horizontalAlignment = Alignment.CenterHorizontally){

        TopBar(message = "Test", coins = 7, navController = navController)

//        Image(painter = painterResource(id = R.drawable.red_flag), contentDescription = null, modifier = Modifier.padding(top=40.dp))
//
//
//
//        val options = mutableListOf<String>("qwerkah fsgafsdgjhh df", "dfgh", "zxcv", "bxcvx")
//
//        Box(modifier = Modifier.fillMaxSize().padding(16.dp)){
//
//            LazyVerticalGrid(columns = GridCells.Fixed(2),
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = Modifier.align(Alignment.BottomCenter)){
//
//                items(options){option ->
//
//                    OptionItem(text = option)
//
//                }
//            }
//        }

        Column(modifier = Modifier.fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.red_flag),
                contentDescription = null,
                modifier = Modifier.weight(1f))

            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(text = "dfsk")
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(text = "asda")
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(text = "uipoty")
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(text = "lkwhf")
                    }
                }
            }
        }
    }
}

@Composable
fun OptionItem(text: String){

    Box(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(15.dp))
//        .background(myBlue)
//        .padding(3.dp)
//        .clip(RoundedCornerShape(15.dp))
        .background(Color.White)
        .padding(10.dp),
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