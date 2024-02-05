package uz.itschool.geo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import uz.itschool.geo.R
import uz.itschool.geo.helpers.SharedPrefHelper
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.model.Level
import uz.itschool.geo.navigation.Screens

@Composable
fun SplashScreen(navHostController: NavController){
    val context = LocalContext.current
    val shared = SharedPrefHelper.getInstance(context)

    val appDatabase: AppDataBase by lazy {
        AppDataBase.getInstance(context)
    }


    LaunchedEffect(key1 = true){
        delay(3000)
        if(!shared.getDBState()){
            shared.setDBState(true)
            createDB(appDatabase)
        }
        navHostController.navigate(Screens.Home.route)
    }
    Box(modifier = Modifier.fillMaxSize()){

    }
}

fun createDB(appDataBase: AppDataBase){
    val countryDao = appDataBase.getCountryDao()

    countryDao.addCountry(Country(
        name = "Argentina",
        capital = "Buenos Aires",
        level = Level.STUDENT,
        flag = R.drawable.ar))

    countryDao.addCountry(Country(
        name = "Australia",
        capital = "Canberra",
        level = Level.STUDENT,
        flag = R.drawable.au))

    countryDao.addCountry(Country(
        name = "Austria",
        capital = "Vienna",
        level = Level.STUDENT,
        flag = R.drawable.at))

    countryDao.addCountry(Country(
        name = "Brazil",
        capital = "Brasilia",
        level = Level.STUDENT,
        flag = R.drawable.br))

    countryDao.addCountry(Country(
        name = "Canada",
        capital = "Ottawa",
        level = Level.STUDENT,
        flag = R.drawable.ca))

    countryDao.addCountry(Country(
        name = "China",
        capital = "Beijing",
        level = Level.STUDENT,
        flag = R.drawable.cn))

    countryDao.addCountry(Country(
        name = "France",
        capital = "Paris",
        level = Level.STUDENT,
        flag = R.drawable.fr))

    countryDao.addCountry(Country(
        name = "Germany",
        capital = "Berlin",
        level = Level.STUDENT,
        flag = R.drawable.de))

    countryDao.addCountry(Country(
        name = "India",
        capital = "New Delhi",
        level = Level.STUDENT,
        flag = R.drawable.`in`))

    countryDao.addCountry(Country(
        name = "Iran",
        capital = "Tehran",
        level = Level.STUDENT,
        flag = R.drawable.ir))

    countryDao.addCountry(Country(
        name = "Italy",
        capital = "Rome",
        level = Level.STUDENT,
        flag = R.drawable.it))

    countryDao.addCountry(Country(
        name = "Japan",
        capital = "Tokyo",
        level = Level.STUDENT,
        flag = R.drawable.jp))

    countryDao.addCountry(Country(
        name = "Netherlands",
        capital = "Amsterdam",
        level = Level.STUDENT,
        flag = R.drawable.nl))

    countryDao.addCountry(Country(
        name = "Russia",
        capital = "Moscow",
        level = Level.STUDENT,
        flag = R.drawable.ru))

    countryDao.addCountry(Country(
        name = "South Korea",
        capital = "Seoul",
        level = Level.STUDENT,
        flag = R.drawable.kr))

    countryDao.addCountry(Country(
        name = "Spain",
        capital = "Madrid",
        level = Level.STUDENT,
        flag = R.drawable.fr))

    countryDao.addCountry(Country(
        name = "Switzerland",
        capital = "Bern",
        level = Level.STUDENT,
        flag = R.drawable.au))

    countryDao.addCountry(Country(
        name = "Turkey",
        capital = "Ankara",
        level = Level.STUDENT,
        flag = R.drawable.bt))

    countryDao.addCountry(Country(
        name = "United Kingdom",
        capital = "London",
        level = Level.STUDENT,
        flag = R.drawable.au))

    countryDao.addCountry(Country(
        name = "United States",
        capital = "Washington(Biden)",
        level = Level.STUDENT,
        flag = R.drawable.au))



}


@Preview(showBackground = true)
@Composable
fun test(){
    val navController = rememberNavController()
    SplashScreen(navController)
}