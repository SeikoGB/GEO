package uz.itschool.geo.localDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.itschool.geo.model.CategoryType
import uz.itschool.geo.model.LevelType

@Entity(tableName = "levels")
data class Level(
    @PrimaryKey
    val id: Int = 0,
    val levelType: LevelType,
    val categoryName: String,
    val maxQuestion: Int = 20,
)
