package uz.itschool.geo.model

import uz.itschool.geo.R

enum class CategoryType(val text: String, val img: Int) {
    BY_FLAG("Flag", R.drawable.red_flag),
    BY_CAPITAL("Capital City", R.drawable.capital_city_icon),
}

//val allCategoryType = mutableListOf(CategoryType.BY_FLAG, CategoryType.BY_CAPITAL)