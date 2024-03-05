package uz.itschool.geo.localDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.itschool.geo.localDatabase.entity.Level
import uz.itschool.geo.model.CategoryType


@Dao
interface LevelDao {

    @Insert
    fun addLevel(level: Level)

    @Query("SELECT * FROM levels WHERE categoryName = :categoryName")
    fun getLevelByCategory(categoryName: String): MutableList<Level>

    @Query("SELECT * FROM levels WHERE levelName = :levelName")
    fun getLevelByName(levelName: String):Level

    @Query("SELECT * FROM levels")
    fun getAllLevels(): MutableList<Level>

    @Update
    fun editLevel(level: Level)
}