package uz.itschool.geo.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.geo.Category
import uz.itschool.geo.R
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.navigation.Screens
import uz.itschool.geo.ui.theme.myBlue
import uz.itschool.geo.ui.theme.myGreen
import uz.itschool.geo.ui.theme.myLightBlue
import uz.itschool.geo.ui.theme.myOrange
import uz.itschool.geo.ui.theme.myRed
import uz.itschool.geo.ui.theme.myYellow
import uz.itschool.geo.ui.theme.whiteBackround


@Composable
fun HomeScreen(navController: NavController){

    val categories = mutableListOf<Category>()

    categories.add(Category("By flag", "gkf", 45))
    categories.add(Category("By flag", "gkf", 45))
    categories.add(Category("By flag", "gkf", 45))
    categories.add(Category("By flag", "gkf", 45))

    val context = LocalContext.current

    val appDatabase: AppDataBase by lazy {
        AppDataBase.getInstance(context)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(whiteBackround),
        contentAlignment = Alignment.TopStart){

        HomeTopBar()


        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "Country",
                fontSize = 30.sp,
                color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)){

                items(categories){category ->
                    CategoryItem(category = category,
                        navController = navController)

                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Competition()
        }

        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .padding(16.dp)){
            HomeBottomBar()
        }
    }
}


@Composable
fun CategoryItem(category: Category, navController: NavController){

    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
        .clickable {
            categoryItemClicked(navController)
        }){

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.red_flag),
                contentDescription = null,
                modifier = Modifier.height(130.dp))

            Text(text = category.name,
                fontSize = 20.sp,
                color = myRed,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = "${category.solvedNumber}/194",
                color = myRed,
                fontSize = 12.sp)


        }
    }
}


@Composable
fun HomeTopBar(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .clip(
            RoundedCornerShape(
                bottomStartPercent = 20,
                bottomEndPercent = 20
            )
        )
        .background(myBlue)
    )
}


@Composable
fun Competition(){

    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
        .background(Color.White)
        .padding(5.dp),
        ){

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.trophy),
                contentDescription = null,
                modifier = Modifier.size(50.dp))

            Text(modifier = Modifier.weight(1f),
                text = "Competitive Games",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = myOrange
            )
        }
    }
}


@Composable
fun HomeBottomBar(){

    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
        .background(Color.White)
        .padding(5.dp)
        ){

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()) {


            Icon(imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = myYellow,
                modifier = Modifier.size(50.dp))

            Icon(imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                tint = myGreen,
                modifier = Modifier.size(50.dp))

            Icon(imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = myLightBlue,
                modifier = Modifier.size(50.dp))

        }
    }
}

fun categoryItemClicked(navController: NavController){
    navController.navigate(Screens.Level.route)
}

@Preview(showBackground = true)
@Composable
fun hometest(){
    val navController = rememberNavController()
    HomeScreen(navController)
}