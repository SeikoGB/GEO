package uz.itschool.geo.screens.TestScreen

import uz.itschool.geo.App
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country

class TestModel(l:String) {
    val localDB = AppDataBase.getInstance(App.app)
    var countryList:MutableList<Country> = localDB.getCountryDao().getByLevel(l)


}