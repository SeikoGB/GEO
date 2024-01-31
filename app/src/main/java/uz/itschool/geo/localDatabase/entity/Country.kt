package uz.itschool.geo.localDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.itschool.geo.model.Level


@Entity(tableName = "countries")
data class Country(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val capital: String,
    val level:Level,
    val flag:Int
)