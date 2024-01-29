package uz.itschool.geo.localDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.geo.localDatabase.entity.Country

@Dao
interface CountryDao {

    @Insert
    fun addCountry(country: Country)

    @Query("SELECT * FROM countries")
    fun getAllCountries():MutableList<Country>


}