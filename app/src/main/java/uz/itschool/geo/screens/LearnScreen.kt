package uz.itschool.geo.screens

import android.annotation.SuppressLint
import android.graphics.Paint.Style
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.ui.theme.whiteBackround


@SuppressLint("MutableCollectionMutableState")
@Composable
fun LearnScreen(navController: NavController) {

    val context = LocalContext.current
    val appDatabase: AppDataBase by lazy {
        AppDataBase.getInstance(context)
    }

    val countries by remember {
        mutableStateOf(appDatabase.getCountryDao().getAllCountries())
    }
    var dialogState by remember {
        mutableStateOf(false)
    }
    var chosenCountry by remember {
        mutableStateOf(countries[0])
    }

    if (dialogState){
        AlertDialog(
            onDismissRequest = { dialogState = false },
            text = { LearnDialog(country = chosenCountry)},
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
                if(LearnCountryItem(country = country)){
                    dialogState = true
                    chosenCountry = country
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun LearnCountryItem(country: Country): Boolean{

    var isclicked by remember {
        mutableStateOf(false)
    }

    Box (modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)
        .padding(10.dp)
        .clickable {
            isclicked = true
        }){

        Row (modifier = Modifier.fillMaxWidth()){
            Text(text = country.name,
                fontSize = 25.sp,
                modifier = Modifier.weight(1f))

            Box(modifier = Modifier
                .fillMaxHeight()
                .padding(5.dp)
                .width(5.dp)
                .background(Color.Black))

            Text(text = country.capital,
                fontSize = 25.sp,
                modifier = Modifier.weight(1f))
        }
    }

    return isclicked
}

@Composable
fun LearnDialog(country: Country){

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)) {

        Text(text = "Flag",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(painter = painterResource(id = country.flag),
            contentDescription = "flag")

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Country: ${country.name}",
            fontSize = 25.sp,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Country: ${country.capital}",
            fontSize = 25.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun learntest(){
    val navController = rememberNavController()
    LearnScreen(navController)
}