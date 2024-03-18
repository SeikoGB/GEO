@file:OptIn(ExperimentalFoundationApi::class)

package uz.itschool.geo.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.geo.R
import uz.itschool.geo.model.CategoryHolder
import uz.itschool.geo.model.CategoryType
import uz.itschool.geo.navigation.Screens
import uz.itschool.geo.ui.theme.myBlue
import uz.itschool.geo.ui.theme.myGreen
import uz.itschool.geo.ui.theme.myLightBlue
import uz.itschool.geo.ui.theme.myOrange
import uz.itschool.geo.ui.theme.myRed
import uz.itschool.geo.ui.theme.myYellow
import uz.itschool.geo.ui.theme.whiteBackround


@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomeScreen(navController: NavController){

    val categories by remember {
        mutableStateOf(mutableListOf(
            CategoryHolder.COUNTRY,
            CategoryHolder.CAPITAL,
            CategoryHolder.FLAG))
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(whiteBackround),
        contentAlignment = Alignment.TopStart){

        HomeTopBar()

        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(16.dp))
//
//            LazyVerticalGrid(columns = GridCells.Fixed(2),
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = Modifier.weight(1f)){
//
//                items(categories){category ->
//                    CategoryItem(category = category,
//                        navController = navController)
//                }
//            }


            LazyColumn(modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)){
                items(categories){categoryHolder->
                    Text(text = categoryHolder.text,
                        fontSize = 30.sp,
                        color = Color.White,)

                    LazyRow(modifier = Modifier.fillMaxWidth(),){
                        items(categoryHolder.categories){category->
                            CategoryItem(category = category,
                                navController = navController)

                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                }
            }

            //Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)){

                Competition()

                Spacer(modifier = Modifier.height(20.dp))

                LearnButton(navController)

                Spacer(modifier = Modifier.height(20.dp))

                HomeBottomBar()
            }
        }
    }
}

@Composable
fun HomeWithPager(navController: NavController){
    val categories by remember {
        mutableStateOf(mutableListOf(
            CategoryHolder.COUNTRY,
            CategoryHolder.CAPITAL,
            CategoryHolder.FLAG))
    }
    val pagerState= rememberPagerState(pageCount = {categories.size})

    Box(modifier = Modifier
        .fillMaxSize()
        .background(whiteBackround),
        contentAlignment = Alignment.TopStart){

        HomeTopBar()

        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,) {

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPager(state = pagerState ) { categoryHolder->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = categories[categoryHolder].text,
                        fontSize = 30.sp,
                        color = Color.White,)
                    LazyRow(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround){
                        items(categories[categoryHolder].categories){category->
                            CategoryItem(category = category,
                                navController = navController)

                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                }

            }
        }


            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
                ){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                ){
                Competition()
                LearnButton(navController)

                Spacer(modifier = Modifier.height(20.dp))

                HomeBottomBar()
            }
        }

    }
}

@Composable
fun CategoryItem(category: CategoryType,
                 navController: NavController){
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
        .clickable {
            navController.navigate(Screens.Level.passCategoryType(category.path))
        }){

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = category.img),
                contentDescription = null,
                modifier = Modifier.height(130.dp))

            Text(text = category.text,
                fontSize = 20.sp,
                color = myRed,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(5.dp))
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
fun LearnButton(navController: NavController){

    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
        .clickable {
            navController.navigate(Screens.Learn.route)
        }
        .background(Color.White)
        .padding(5.dp)
    ){

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.learn_icon),
                contentDescription = null,
                modifier = Modifier.size(50.dp))

            Text(modifier = Modifier.weight(1f),
                text = "Learn",
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

@Preview(showBackground = true)
@Composable
fun hometest(){
    val navController = rememberNavController()
    HomeWithPager(navController = navController)
}