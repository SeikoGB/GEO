package uz.itschool.geo.screens.TestScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.itschool.geo.ui.theme.myBlue
import uz.itschool.geo.ui.theme.whiteBackround

@Composable
fun TextByTextTestScreen(navController: NavController,
                         viewModel: TestViewModel){

    val currentQuestion = viewModel.currentQuestion.observeAsState().value!!
    val isGameFinished = viewModel.isGameFinished.observeAsState().value!!

    if (isGameFinished){
        viewModel.finishGame(navController)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(whiteBackround),
        horizontalAlignment = Alignment.CenterHorizontally){

        TestTopBar(viewModel, navController)

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Box(modifier = Modifier.weight(1f)
            ){
                Box(modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(CornerSize(25)))
                    .background(myBlue)
                    .padding(3.dp)
                    .clip(RoundedCornerShape(CornerSize(25)))
                    .background(Color.White)
                    .padding(10.dp),
                    contentAlignment = Alignment.Center){

                    Text(text = viewModel.getStringQuestion(currentQuestion),
                        modifier = Modifier.clip(RoundedCornerShape(CornerSize(15.dp))),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier
                        .weight(1f)){
                        TextOptionItem(viewModel = viewModel, index = 0)
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        TextOptionItem(viewModel = viewModel, index = 1)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.weight(1f)){
                        TextOptionItem(viewModel = viewModel, index = 2)
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        TextOptionItem(viewModel = viewModel, index = 3)
                    }
                }
            }
        }
    }
}