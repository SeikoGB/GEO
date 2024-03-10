package uz.itschool.geo.model

import uz.itschool.geo.R

enum class CategoryType(val text: String, val path: String, val img: Int) {
    COUNTRY_BY_FLAG("Flag", "country_by_flag", R.drawable.red_flag),
    COUNTRY_BY_CAPITAL("Capital", "country_by_capital", R.drawable.capital_city_icon),

    CAPITAL_BY_FLAG("Flag", "capital_by_flag", R.drawable.red_flag),
    CAPITAL_BY_COUNTRY("Country", "capital_by_country", R.drawable.globe_icon),

    FLAG_BY_COUNTRY("Country", "flag_by_country", R.drawable.globe_icon),
    FLAG_BY_CAPITAL("Capital", "flag_by_capital", R.drawable.capital_city_icon),
}

val allCategoryTypes = mutableListOf(
    CategoryType.COUNTRY_BY_FLAG,
    CategoryType.COUNTRY_BY_CAPITAL,
    CategoryType.CAPITAL_BY_FLAG,
    CategoryType.CAPITAL_BY_COUNTRY,
    CategoryType.FLAG_BY_COUNTRY,
    CategoryType.FLAG_BY_CAPITAL,
    )