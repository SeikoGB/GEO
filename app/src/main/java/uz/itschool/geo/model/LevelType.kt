package uz.itschool.geo.model

import uz.itschool.geo.R

enum class LevelType(val text:String, val image:Int) {
    STUDENT("Student", R.drawable.student),
    TOURIST("Tourist", R.drawable.traveller),
    DRIVER("Driver", R.drawable.driver),
    CAPTAIN("Captain", R.drawable.captain),
    TEACHER("Teacher", R.drawable.teacher),
    SCIENTIST("Scientist",R.drawable.chemistry),
    VIRGIN_OPENER("Land Opener",R.drawable.virgin_opener)
}