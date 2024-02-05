package uz.itschool.geo.localDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.model.Level

@Dao
interface CountryDao {

    @Insert
    fun addCountry(country: Country)

    @Query("SELECT * FROM countries")
    fun getAllCountries():MutableList<Country>

    @Query("SELECT * FROM countries WHERE level = :l")
    fun getByLevel(l: Level):MutableList<Country>




}