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
val allLevels = mutableListOf(
    LevelType.STUDENT,
    LevelType.TOURIST,
    LevelType.DRIVER,
    LevelType.CAPTAIN,
    LevelType.TEACHER,
    LevelType.SCIENTIST,
    LevelType.VIRGIN_OPENER
)

fun getLevelImgByText(text: String):Int{
    var levelType = allLevels[0]
    for (level in allLevels){
        if (level.text == text){
            levelType = level
        }
    }
    return levelType.image
}