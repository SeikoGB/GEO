package uz.itschool.geo.navigation


const val PASS_CATEGORY_TYPE = "category_type"
const val PASS_LEVEL_ID = "level_id"

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

    object Test: Screens("test/{$PASS_LEVEL_ID}"){
        fun passLevelId(id: Int): String{
            return this.route.replace(
                oldValue = "{$PASS_LEVEL_ID}",
                newValue = id.toString()
            )
        }
    }

    object Learn: Screens("learn")

    object Result: Screens("result")

}
