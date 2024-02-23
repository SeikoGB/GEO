package uz.itschool.geo.screens.TestScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import uz.itschool.geo.screens.levelScreen.TopBar
import uz.itschool.geo.ui.theme.myBlue

@Composable
fun TestScreen(navController: NavController,
               viewModel: TestViewModel){

    var isRandomed by remember {
        mutableStateOf(false)
    }

    if(!isRandomed){
        viewModel.randomiseList()
        isRandomed = true
    }

    val countries = viewModel.countries.observeAsState().value!!
    val timeProgress = viewModel.timeProgress.observeAsState().value!!
    val questionNumber = viewModel.questionNumber.observeAsState().value!!


    Log.d("TAG", "TestScreen: ${countries}")

    val currentQuestion = viewModel.currentQuestion



    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        TestTopBar(time = timeProgress)


        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = currentQuestion.flag),
                contentDescription = null,
                modifier = Modifier.weight(1f))

            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier
                        .weight(1f)
                        .clickable { viewModel.nextQuestion() }){
                        OptionItem(text = currentQuestion.name)
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
        .background(myBlue)
        .padding(3.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(Color.White)
        .padding(10.dp),
        contentAlignment = Alignment.Center){
        Text(text = text,
            fontSize = 20.sp)
    }
}

@Composable
fun TestTopBar(time: String){
    Box(modifier = Modifier.fillMaxWidth()){
        Text(text = time)
    }
}


@Preview(showBackground = true)
@Composable
fun testtest(){
    val navController = rememberNavController()
    val viewModel = TestViewModel("levelName")
    TestScreen(navController, viewModel)
}