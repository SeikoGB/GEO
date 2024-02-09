package uz.itschool.geo.navigation

import uz.itschool.geo.model.CategoryType

const val PASS_CATEGORY_TYPE = "category_type"

sealed class Screens(var route: String) {


    object Splash: Screens("splash")
    object Home: Screens("home")

    object Level: Screens("level/{$PASS_CATEGORY_TYPE}"){
        fun passCategoryType(category: CategoryType): String{
            return this.route.replace(
                oldValue = "{$PASS_CATEGORY_TYPE}",
                newValue = category.toString()
            )
        }
    }

    object Test: Screens("test")

}