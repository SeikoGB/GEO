package uz.itschool.geo.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.itschool.geo.localDatabase.dao.CountryDao
import uz.itschool.geo.localDatabase.dao.LevelDao
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.localDatabase.entity.Level


@Database(entities = [Country::class, Level::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getCountryDao(): CountryDao

    abstract fun getLevelDao(): LevelDao

    companion object{
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase{
            if (instance==null){
                instance = Room.databaseBuilder(context,
                    AppDataBase::class.java, "app_db")
                    //.addTypeConverter(Converters::class)
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}