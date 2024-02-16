package uz.itschool.geo.screens.learnScreen

import android.content.Context
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country

class LearnModel(context: Context){

    val localDB = AppDataBase.getInstance(context)

    fun getCountryList():MutableList<Country>{
        return localDB.getCountryDao().getAllCountries()
    }




}