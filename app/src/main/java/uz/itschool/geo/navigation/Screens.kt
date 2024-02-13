package uz.itschool.geo.navigation

import uz.itschool.geo.model.CategoryType

const val PASS_CATEGORY_TYPE = "category_type"

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

    object Test: Screens("test")

}
