package uz.itschool.geo.model

import uz.itschool.geo.localDatabase.entity.Country

class LevelAdapter(level: LevelType, list: MutableList<Country>) {
    var solvedTest = 0
    val allTest = list.size

}