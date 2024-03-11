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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
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
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.ui.theme.myBlue
import uz.itschool.geo.ui.theme.myRed
import uz.itschool.geo.ui.theme.whiteBackround

@Composable
fun TextByImgTestScreen(navController: NavController,
                        viewModel: TestViewModel){

    val isGameFinished = viewModel.isGameFinished.observeAsState().value!!
    val currentQuestion=viewModel.currentQuestion.observeAsState().value!!

    if (isGameFinished){
        viewModel.finishGame(navController)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(whiteBackround),
        horizontalAlignment = Alignment.CenterHorizontally){

        TestTopBar(viewModel)

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
                        .weight(1f)){
                        OptionItem(viewModel = viewModel, index = 0)
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(viewModel = viewModel, index = 1)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(viewModel = viewModel, index = 2)
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        OptionItem(viewModel = viewModel, index = 3)
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
fun OptionItem(viewModel: TestViewModel, index: Int){

    val statee = viewModel.answersState.observeAsState()
    val state=statee.value!![index]

    var cardBG by remember {
        mutableStateOf(Color.White)
    }
    var textColor by remember {
        mutableStateOf(Color.Black)
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(15.dp))
        .background(myBlue)
        .clickable(state) {

            viewModel.checkQuestion(index)

            Log.d("wrong ans", "OptionItem: $state")

            if (!state) {
                cardBG = myRed
                textColor = Color.White
            }
        }
        .padding(3.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(cardBG)
        .padding(10.dp),
        contentAlignment = Alignment.Center){
        Text(
            text = viewModel.answers.value!![index].name,
            color = textColor,
            fontSize = 20.sp)
    }
}

@Composable
fun TestTopBar(viewModel: TestViewModel){
    val timeProgress = viewModel.timeProgress.observeAsState().value!!
    val lives = viewModel.lives.observeAsState().value!!

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(myBlue),
        horizontalArrangement = Arrangement.SpaceEvenly){

        Text(text = "Lives: $lives")

        Text(text = viewModel.thisLevel.levelName)

        Box(modifier = Modifier
            //.fillMaxHeight(),
            ,
            contentAlignment = Alignment.Center){
            Text(text = timeProgress,
                color = Color.White,
                fontSize = 25.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun testtest(){
    val navController = rememberNavController()
    val viewModel = TestViewModel("levelName")
    TextByImgTestScreen(navController, viewModel)
}