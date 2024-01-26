package uz.itschool.geo.navigation

sealed class Screens(var route: String) {


    object Splash: Screens("splash")
    object Home: Screens("home")
    object Level: Screens("level")



}