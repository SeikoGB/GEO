package uz.itschool.geo.model

enum class CategoryHolder(val text: String, val categories: MutableList<CategoryType>) {
    COUNTRY("Find countries by: ",
        mutableListOf(CategoryType.COUNTRY_BY_FLAG,
            CategoryType.COUNTRY_BY_CAPITAL, CategoryType.COUNTRY_BY_CAPITAL)),

    CAPITAL("Find capitals by: ",
        mutableListOf(CategoryType.CAPITAL_BY_COUNTRY,
            CategoryType.CAPITAL_BY_FLAG))

}