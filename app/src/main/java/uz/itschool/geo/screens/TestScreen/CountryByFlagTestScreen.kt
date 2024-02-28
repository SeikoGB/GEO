package uz.itschool.geo.screens.TestScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import uz.itschool.geo.ui.theme.myBlue
import uz.itschool.geo.ui.theme.whiteBackround

@Composable
fun TestScreen(navController: NavController,
               viewModel: TestViewModel){

    val timeProgress = viewModel.timeProgress.observeAsState().value!!
    //val questionNumber = viewModel.questionNumber.observeAsState().value!!
    val currentQuestion = viewModel.currentQuestion.observeAsState().value!!
    val answers = viewModel.answers.observeAsState().value!!
    val score = viewModel.score.observeAsState().value!!

    Log.d("answer", "TestScreen: $answers")

    Column(modifier = Modifier
        .fillMaxSize()
        .background(whiteBackround),
        horizontalAlignment = Alignment.CenterHorizontally){

        TestTopBar(time = timeProgress)


        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {


            Box(modifier = Modifier.weight(1f)
            ){

                Box(modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(CornerSize(15.dp)))
                    .background(myBlue)
                    .padding(3.dp)){

                    Image(painter = painterResource(id = currentQuestion.flag),
                        contentDescription = null,
                        modifier = Modifier.clip(RoundedCornerShape(15.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier
                        .weight(1f)
                        .clickable {
                            viewModel.nextQuestion()
                        }){
                        OptionItem(text = answers[0].name, viewModel)
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(text = answers[1].name, viewModel)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(text = answers[2].name, viewModel)
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(text = answers[3].name, viewModel)
                    }
                }
            }


//            LazyVerticalGrid(
//                columns = GridCells.Fixed(2),
//                verticalArrangement = Arrangement.SpaceEvenly,
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = Modifier.weight(1f)){
//
//                items(answers){answer ->
//                    OptionItem(text = answer.name, viewModel = viewModel)
//                }
//
//            }
        }
    }
}

@Composable
fun OptionItem(text: String, viewModel: TestViewModel){

    Box(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(15.dp))
        .background(myBlue)
        .clickable {
            optionItemClicked(viewModel)
        }
        .padding(3.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(Color.White)
        .padding(10.dp),
        contentAlignment = Alignment.Center){
        Text(
            text = text,
            color = Color.Black,
            fontSize = 20.sp)
    }
}

fun optionItemClicked(viewModel: TestViewModel){
    viewModel.stopTimer()
    viewModel.startTimer()
    viewModel.nextQuestion()
    viewModel.updateAnswers()
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