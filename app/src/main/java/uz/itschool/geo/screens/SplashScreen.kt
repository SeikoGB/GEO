package uz.itschool.geo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Database
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.delay
import uz.itschool.geo.helpers.SharedPrefHelper
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country
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
        if(!check(shared)){
            createDB(appDatabase)
        }
        navHostController.navigate(Screens.Home.route)
    }

    Box(modifier = Modifier.fillMaxSize()){

    }
}

fun check(shared: SharedPrefHelper): Boolean{
    return shared.getDBState()
}

fun createDB(appDataBase: AppDataBase){
    val countryDao = appDataBase.getCountryDao()

    countryDao.addCountry(Country(0, "Uzbekistan", "Tashkent"))
}


@Preview(showBackground = true)
@Composable
fun test(){
    val navController = rememberNavController()
    SplashScreen(navController)
}