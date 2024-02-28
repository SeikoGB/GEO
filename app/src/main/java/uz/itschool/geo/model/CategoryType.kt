package uz.itschool.geo.model

import uz.itschool.geo.R

enum class CategoryType(val text: String, val img: Int) {
    COUNTRY_BY_FLAG("Country by flag", R.drawable.red_flag),
    COUNTRY_BY_CAPITAL("Country by capital", R.drawable.capital_city_icon),
}

//val allCategoryType = mutableListOf(CategoryType.BY_FLAG, CategoryType.BY_CAPITAL)