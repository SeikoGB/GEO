package uz.itschool.geo.screens.TestScreen

import android.graphics.Paint.Style
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.itschool.geo.ui.theme.myBlue
import uz.itschool.geo.ui.theme.myRed
import uz.itschool.geo.ui.theme.whiteBackround

@Composable
fun ImgByTextTestScreen(navController: NavController,
                        viewModel: TestViewModel){

    val isGameFinished = viewModel.isGameFinished.observeAsState().value!!

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

                    Text(text = viewModel.getStringQuestion(),
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
                        ImageOptionItem(viewModel = viewModel, index = 0)
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        ImageOptionItem(viewModel = viewModel, index = 1)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.weight(1f)){
                        ImageOptionItem(viewModel = viewModel, index = 2)
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(modifier = Modifier.weight(1f)){
                        ImageOptionItem(viewModel = viewModel, index = 3)
                    }
                }
            }

        }
    }
}


@Composable
fun ImageOptionItem(viewModel: TestViewModel, index: Int){

    val state = viewModel.answersState.observeAsState().value!![index]

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

            //Log.d("wrong ans", "OptionItem: $state")

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
        Image(
            painter = painterResource(
                id = viewModel.answers.value!![index].flag),
            contentDescription = null)
    }

}