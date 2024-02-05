package uz.itschool.geo.localDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.geo.localDatabase.entity.Level
import uz.itschool.geo.model.CategoryType


@Dao
interface LevelDao {

    @Insert
    fun addLevel(level: Level)

    @Query("SELECT * FROM levels WHERE categoryType = :category")
    fun getLevelByCategory(category: CategoryType): MutableList<Level>

}