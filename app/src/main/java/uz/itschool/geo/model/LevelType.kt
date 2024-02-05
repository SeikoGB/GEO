package uz.itschool.geo.model

import uz.itschool.geo.R

enum class LevelType(val psevdoname:String, val image:Int) {
    STUDENT("student", R.drawable.coin),
    TOURIST("Touris", R.drawable.coin),
    DRIVER("Driver", R.drawable.coin),
    CAPTAIN("Captain", R.drawable.coin),
    TEACHER("Teacher", R.drawable.coin),
}