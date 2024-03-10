package uz.itschool.geo.model

enum class CategoryHolder(val text: String, val categories: MutableList<CategoryType>) {
    COUNTRY("Countries by: ",
        mutableListOf(CategoryType.COUNTRY_BY_FLAG,
            CategoryType.COUNTRY_BY_CAPITAL,)),

    CAPITAL("Capitals by: ",
        mutableListOf(CategoryType.CAPITAL_BY_COUNTRY,
            CategoryType.CAPITAL_BY_FLAG)),

    FLAG("Flags by:",
        mutableListOf(CategoryType.FLAG_BY_COUNTRY,
            CategoryType.FLAG_BY_CAPITAL))

}