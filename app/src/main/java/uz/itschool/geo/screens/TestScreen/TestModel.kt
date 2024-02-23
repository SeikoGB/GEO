package uz.itschool.geo.screens.TestScreen

import uz.itschool.geo.App
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country

class TestModel(val level:String) {
    val localDB = AppDataBase.getInstance(App.app)


    fun getCountryList(): MutableList<Country>{
        var countryList:MutableList<Country> = localDB.getCountryDao().getByLevel(level)
        return countryList
    }



}