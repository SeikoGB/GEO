package uz.itschool.geo.localDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.itschool.geo.model.LevelType


@Entity(tableName = "countries")
data class Country(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val capital: String,
    val levelType:LevelType,
    val flag:Int
)