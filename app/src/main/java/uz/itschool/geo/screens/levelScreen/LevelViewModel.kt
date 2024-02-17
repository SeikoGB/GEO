package uz.itschool.geo.screens.levelScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.itschool.geo.App
import uz.itschool.geo.helpers.SharedPrefHelper
import uz.itschool.geo.localDatabase.AppDataBase

class LevelViewModel(): ViewModel() {
    private val localDB = AppDataBase.getInstance(App.app)
     val shared = SharedPrefHelper.getInstance(App.app)

    private val _currentLevels = MutableLiveData(localDB.getLevelDao().getAllLevels())
    val currentLevels: LiveData<MutableList<uz.itschool.geo
        .localDatabase.entity.Level>> = _currentLevels

    fun setLevelsByCategory(categoryName: String){
        _currentLevels.value = localDB.getLevelDao().getLevelByCategory(categoryName)
    }

}