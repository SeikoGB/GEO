package uz.itschool.geo.navigation


const val PASS_CATEGORY_TYPE = "category_type"
const val PASS_LEVEL_TYPE = "level_type"

sealed class Screens(var route: String) {


    object Splash: Screens("splash")
    object Home: Screens("home")

    object Level: Screens("level/{$PASS_CATEGORY_TYPE}"){
        fun passCategoryType(categoryName: String): String{
            return this.route.replace(
                oldValue = "{$PASS_CATEGORY_TYPE}",
                newValue = categoryName
            )
        }
    }

    object Test: Screens("test/{$PASS_LEVEL_TYPE}/{$PASS_CATEGORY_TYPE}"){
        fun passLevelAndCategoryType(levelName: String, categoryName: String): String{
            return this.route.replace(
                oldValue = "{$PASS_LEVEL_TYPE}/{$PASS_CATEGORY_TYPE}",
                newValue = "{$levelName}/{$categoryName}"
            )
        }
    }

    object Learn: Screens("learn")

}
