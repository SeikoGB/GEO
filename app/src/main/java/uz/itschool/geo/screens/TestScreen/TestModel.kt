package uz.itschool.geo.screens.TestScreen

import uz.itschool.geo.App
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.localDatabase.entity.Level

class TestModel {
    val localDB = AppDataBase.getInstance(App.app)
    val listSize = localDB.getLevelDao().getAllLevels().size
    var thisLevel: Level = Level(levelName = "", categoryName = "")

    fun getCountryList(id: Int): MutableList<Country>{
        val countryList:MutableList<Country> =
            localDB
                .getCountryDao()
                .getByLevel(
                    getLevel(id).levelName)
        return countryList
    }

    fun getLevel(id: Int): Level{
        return localDB.getLevelDao().getLevelById(id)
    }

    fun setLevel(id: Int){
        thisLevel = getLevel(id)
    }

    fun updateLevel(level: Level){
        val levelDAO = localDB.getLevelDao()
        levelDAO.editLevel(level)
    }

    fun unlockLevel(id: Int){
        if (id<listSize){
            val level = localDB.getLevelDao().getLevelById(id)
            level.isOpened = true
            updateLevel(level)
        }
    }
}