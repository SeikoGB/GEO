package uz.itschool.geo.screens.TestScreen

import androidx.lifecycle.ViewModel
import uz.itschool.geo.App
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country

class TestModel: ViewModel() {
    val localDB = AppDataBase.getInstance(App.app)
    var countryList=ArrayList<Country>()
    fun getCountries(l:String){
    countryList=localDB.getCountryDao().getByLevel(l) as ArrayList<Country>
    }

}