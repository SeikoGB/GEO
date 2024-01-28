package uz.itschool.geo.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.itschool.geo.localDatabase.dao.CountryDao
import uz.itschool.geo.localDatabase.entity.Country


@Database(entities = [Country::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getCountryDao(): CountryDao

    companion object{
        var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase{
            if (instance==null){
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "app_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}