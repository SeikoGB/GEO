package uz.itschool.geo.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.geo.R
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.dao.CountryDao
import uz.itschool.geo.model.Level
import uz.itschool.geo.model.LevelAdapter
import uz.itschool.geo.navigation.Screens
import uz.itschool.geo.ui.theme.myBlue


var levels = mutableListOf<LevelAdapter>()

@Composable
fun LevelsScreen(navController: NavController){
    val context = LocalContext.current

    val appDatabase: AppDataBase by lazy {
        AppDataBase.getInstance(context)
    }

    val countryDao = appDatabase.getCountryDao()

    createLevels(countryDao, Level.STUDENT)








    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        TopBar("Sample text", 465, navController)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Levels",
            fontSize = 30.sp)

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)){
            items(levels){level->
                LevelItem(level)

            }
        }
    }

}

@Composable
fun TopBar(message: String,
           coins: Int,
           navController: NavController){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .clip(
            RoundedCornerShape(
                bottomStartPercent = 30,
                bottomEndPercent = 30
            )
        )
        .background(myBlue)
        .padding(horizontal = 16.dp),
    ){
        Row(modifier = Modifier
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically) {

            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
                    .clickable {
                        navController.navigate(Screens.Home.route){
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                })

            Spacer(modifier = Modifier.width(10.dp))

            Text(text = message,
                fontSize = 20.sp,
                color = Color.White)
        }

        Row(modifier = Modifier
            .align(Alignment.CenterEnd)
            .clip(RoundedCornerShape(20.dp))
            .height(30.dp)
            .background(Color.White)
            .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = coins.toString(),
                fontSize = 15.sp)

            Spacer(modifier = Modifier.width(5.dp))

            Image(painter = painterResource(id = R.drawable.coin),
                contentDescription = null,
                modifier = Modifier.size(30.dp))
        }



    }
}

@Composable
fun LevelItem(level: LevelAdapter){

    val progress:Float = (level.solvedTest/level.allTest).toFloat()
    val percent = (progress*100).toInt()

    //Log.d("TAG", "LevelItem: $progress")

    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(30))
        .background(myBlue)
        .padding(3.dp)
        .clip(RoundedCornerShape(30))
        .background(Color.White)
        .padding(10.dp)){

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = "Level ${level.allTest}",
                fontSize = 20.sp)

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "${level.solvedTest}/${level.allTest}")

            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center){

                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .clip(RoundedCornerShape(50)),
                    progress = progress,
                    color = myBlue,)

                Text(text = "${percent}%",
                    color = Color.White)
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

}


fun createLevels(dao: CountryDao, level: Level){
    levels.add(LevelAdapter(level, dao.getByLevel(level)))
}


@Preview(showBackground = true)
@Composable
fun levelTest(){
    val navController = rememberNavController()
    LevelsScreen(navController)
}