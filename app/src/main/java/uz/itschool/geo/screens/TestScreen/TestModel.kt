package uz.itschool.geo.screens.TestScreen

import uz.itschool.geo.App
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.localDatabase.entity.Level

class TestModel(val levelName:String) {
    val localDB = AppDataBase.getInstance(App.app)
    val listSize = localDB.getLevelDao().getAllLevels().size


    fun getCountryList(): MutableList<Country>{
        val countryList:MutableList<Country> = localDB.getCountryDao().getByLevel(levelName)
        return countryList
    }

    fun getLevel(): Level{
        return localDB.getLevelDao().getLevelByName(levelName)
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