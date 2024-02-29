package uz.itschool.geo.model

import uz.itschool.geo.R

enum class CategoryType(val text: String, val img: Int) {
    COUNTRY_BY_FLAG("Flag", R.drawable.red_flag),
    COUNTRY_BY_CAPITAL("Capital", R.drawable.capital_city_icon),

    CAPITAL_BY_FLAG("Flag", R.drawable.red_flag),
    CAPITAL_BY_COUNTRY("Country", R.drawable.globe_icon)
}

//val allCategoryType = mutableListOf(CategoryType.BY_FLAG, CategoryType.BY_CAPITAL)