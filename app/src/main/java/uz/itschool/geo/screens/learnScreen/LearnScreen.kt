package uz.itschool.geo.screens.learnScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.screens.levelScreen.TopBar
import uz.itschool.geo.ui.theme.whiteBackround


@SuppressLint("MutableCollectionMutableState")
@Composable
fun LearnScreen(navController: NavController, viewModel: LearnViewModel) {

    val showDialogState = viewModel.showDialog.observeAsState().value
    val countries = viewModel.countryList.observeAsState().value!!

    if (showDialogState == true){
        AlertDialog(
            onDismissRequest = { viewModel.onDialogDismiss() },
            text = { viewModel.chosenCountry.value?.let { LearnDialog(country = it) } },
            confirmButton = { /*TODO*/ })
    }

    countries.sortBy { it.name }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(whiteBackround)){

        TopBar(message = "", coins = 45, navController = navController)


        LazyColumn(modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 10.dp)){

            items(countries){country: Country ->
                LearnCountryItem(
                    country = country,
                    viewModel = viewModel)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun LearnCountryItem(country: Country, viewModel: LearnViewModel){


    Box (modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)
        .padding(10.dp)
        .clickable {
            viewModel.onOpenDialogClicked()
            viewModel.setChosenCountry(country)
        }){

        Row (modifier = Modifier.fillMaxWidth()){
            Text(text = country.name,
                fontSize = 25.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f))

            Box(modifier = Modifier
                .fillMaxHeight()
                .padding(5.dp)
                .width(5.dp)
                .background(Color.Black))

            Text(text = country.capital,
                fontSize = 25.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun LearnDialog(country: Country){

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)) {

        Text(text = country.name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(painter = painterResource(id = country.flag),
            contentDescription = "flag")

        Spacer(modifier = Modifier.height(30.dp))


        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Capital: ${country.capital}",
            fontSize = 25.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun learntest(){
    val navController = rememberNavController()
    val learnViewModel = LearnViewModel()
    LearnScreen(navController, learnViewModel)
}