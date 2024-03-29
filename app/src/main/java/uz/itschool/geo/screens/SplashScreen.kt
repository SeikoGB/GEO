package uz.itschool.geo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import uz.itschool.geo.App
import uz.itschool.geo.R
import uz.itschool.geo.helpers.SharedPrefHelper
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.dao.CountryDao
import uz.itschool.geo.localDatabase.dao.LevelDao
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.localDatabase.entity.Level
import uz.itschool.geo.model.LevelType
import uz.itschool.geo.model.allCategoryTypes
import uz.itschool.geo.model.allLevelTypes
import uz.itschool.geo.navigation.Screens

@Composable
fun SplashScreen(navHostController: NavController){
    val context = App.app
    val shared = SharedPrefHelper.getInstance(context)

    val appDatabase: AppDataBase by lazy {
        AppDataBase.getInstance(context)
    }


    LaunchedEffect(key1 = true){
        delay(1000)
        if(!shared.getDBState()){
            addCountriesToDB(appDatabase.getCountryDao())
            createLevels(appDatabase.getLevelDao())
            shared.setDBState(true)
        }
        navHostController.navigate(Screens.Home.route)
    }
    Box(modifier = Modifier.fillMaxSize()){
    }
}
fun addCountriesToDB(countryDao: CountryDao){

    countryDao.addCountry(Country(
        name = "Argentina",
        capital = "Buenos Aires",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.ar))

    countryDao.addCountry(Country(
        name = "Australia",
        capital = "Canberra",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.au))

    countryDao.addCountry(Country(
        name = "Austria",
        capital = "Vienna",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.at))

    countryDao.addCountry(Country(
        name = "Brazil",
        capital = "Brasilia",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.br))

    countryDao.addCountry(Country(
        name = "Canada",
        capital = "Ottawa",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.ca))

    countryDao.addCountry(Country(
        name = "China",
        capital = "Beijing",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.cn))

    countryDao.addCountry(Country(
        name = "France",
        capital = "Paris",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.fr))

    countryDao.addCountry(Country(
        name = "Germany",
        capital = "Berlin",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.de))

    countryDao.addCountry(Country(
        name = "India",
        capital = "New Delhi",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.`in`))

    countryDao.addCountry(Country(
        name = "Iran",
        capital = "Tehran",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.ir))

    countryDao.addCountry(Country(
        name = "Italy",
        capital = "Rome",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.it))

    countryDao.addCountry(Country(
        name = "Japan",
        capital = "Tokyo",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.jp))

    countryDao.addCountry(Country(
        name = "Netherlands",
        capital = "Amsterdam",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.nl))

    countryDao.addCountry(Country(
        name = "Russia",
        capital = "Moscow",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.ru))

    countryDao.addCountry(Country(
        name = "South Korea",
        capital = "Seoul",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.kr))

    countryDao.addCountry(Country(
        name = "Spain",
        capital = "Madrid",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.es))

    countryDao.addCountry(Country(
        name = "Switzerland",
        capital = "Bern",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.ch))

    countryDao.addCountry(Country(
        name = "Turkey",
        capital = "Ankara",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.tr))

    countryDao.addCountry(Country(
        name = "United Kingdom",
        capital = "London",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.gb))

    countryDao.addCountry(Country(
        name = "United States",
        capital = "Washington, D.C.",
        levelType = LevelType.STUDENT.text,
        flag = R.drawable.us))

    countryDao.addCountry(Country(
        name = "Algeria",
        capital = "Algiers",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.dz))

    countryDao.addCountry(Country(
        name = "Azerbaijan",
        capital = "Baku",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.az))

    countryDao.addCountry(Country(
        name = "Belgium",
        capital = "Brussels",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.be))

    countryDao.addCountry(Country(
        name = "Colombia",
        capital = "Bogota",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.co))

    countryDao.addCountry(Country(
        name = "Cuba",
        capital = "Havana",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.cu))

    countryDao.addCountry(Country(
        name = "Czechia",
        capital = "Prague",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.cz))
    countryDao.addCountry(Country(
        name = "Denmark",
        capital = "Copnhagen",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.dk))
    countryDao.addCountry(Country(
        name = "Egypt",
        capital = "Cairo",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.eg))
    countryDao.addCountry(Country(
        name = "England",
        capital = "London",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.en))
    countryDao.addCountry(Country(
        name = "Iraq",
        capital = "Baghdad",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.iq))
    countryDao.addCountry(Country(
        name = "Israel",
        capital = "Tel Aviv/ West Jerusalem/ Jerusalem",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.il))
    countryDao.addCountry(Country(
        name = "Mexico",
        capital = "Mexico city",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.mx))
    countryDao.addCountry(Country(
        name = "New Zealand",
        capital = "Wellingtion",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.nz))
    countryDao.addCountry(Country(
        name = "Norway",
        capital = "Oslo",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.no))
    countryDao.addCountry(Country(
        name = "Portugal",
        capital = "Lisbon",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.pt))
    countryDao.addCountry(Country(
        name = "Saudia Arabia",
        capital = "Riyadh",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.sa))
    countryDao.addCountry(Country(
        name = "South Africa",
        capital = "Pretoria-Bloemfontein-Cape Town",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.za))
    countryDao.addCountry(Country(
        name = "Sweden",
        capital = "Stockholm",
        levelType = LevelType.TOURIST.text,
        flag = R.drawable.se))
    countryDao.addCountry(
        Country(
            name = "Syria",
            capital = "Damascus",
            levelType = LevelType.TOURIST.text,
            flag = R.drawable.sy))
    countryDao.addCountry(
        Country(
            name = "United Arabic Emirates",
            capital = "Adu-Dhabi",
            levelType = LevelType.TOURIST.text,
            flag = R.drawable.ae))
    countryDao.addCountry(
        Country(
            name = "Armenia",
            capital = "Yerevan",
            levelType = LevelType.DRIVER.text,
            flag = R.drawable.am))
    countryDao.addCountry(
        Country(
            name = "Chile",
            capital = "Santiago",
            levelType = LevelType.DRIVER.text,
            flag = R.drawable.cl))
    countryDao.addCountry(
        Country(
            name = "Croatia",
            capital = "Zagreb",
            levelType = LevelType.DRIVER.text,
            flag = R.drawable.hr))





}

fun createLevels(levelDao: LevelDao){

    val categories = allCategoryTypes

    val levelTypes = allLevelTypes

    for (c in categories){
        for (l in levelTypes){
            var isOpened = false
            if (l.text == LevelType.STUDENT.text){
                isOpened = true
            }
            levelDao.addLevel(Level(
                categoryName = c.path,
                levelName = l.text,
                isOpened = isOpened))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun test(){
    val navController = rememberNavController()
    SplashScreen(navController)
}