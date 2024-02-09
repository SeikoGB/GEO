package uz.itschool.geo.localDatabase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.geo.localDatabase.entity.Country

class Converters {

    @TypeConverter
    fun fromCountryListToString(countries: MutableList<Country>): String{
        return Gson().toJson(countries)
    }

    @TypeConverter
    fun fromStringToCountryList(string: String): MutableList<Country>{
        val listType = object : TypeToken<MutableList<Country>>() {}.type
        return Gson().fromJson(string, listType)
    }
}