package uz.itschool.geo.model

import uz.itschool.geo.R

enum class LevelType(val text:String, val image:Int, val factor: Int) {
    STUDENT("Student", R.drawable.student, 4),
    TOURIST("Tourist", R.drawable.traveller, 5),
    DRIVER("Driver", R.drawable.driver, 6),
    CAPTAIN("Captain", R.drawable.captain, 7),
    TEACHER("Teacher", R.drawable.teacher, 8),
    SCIENTIST("Scientist",R.drawable.chemistry, 9),
    LAND_OPENER("Land Opener",R.drawable.land_opener, 10)
}
val allLevelTypes = mutableListOf(
    LevelType.STUDENT,
    LevelType.TOURIST,
    LevelType.DRIVER,
    LevelType.CAPTAIN,
    LevelType.TEACHER,
    LevelType.SCIENTIST,
    LevelType.LAND_OPENER
)

fun getLevelImgByText(text: String):Int{
    var levelType = allLevelTypes[0]
    for (level in allLevelTypes){
        if (level.text == text){
            levelType = level
        }
    }
    return levelType.image
}

fun getGetFactorByText(text: String):Int{
    var levelType = allLevelTypes[0]
    for (level in allLevelTypes){
        if (level.text == text){
            levelType = level
            return levelType.factor
        }
    }
    return 0
}