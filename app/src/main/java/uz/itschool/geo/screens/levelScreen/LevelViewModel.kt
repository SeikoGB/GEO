package uz.itschool.geo.screens.levelScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.itschool.geo.App
import uz.itschool.geo.helpers.SharedPrefHelper
import uz.itschool.geo.localDatabase.AppDataBase

class LevelViewModel(private val categoryName: String): ViewModel() {
    private val localDB = AppDataBase.getInstance(App.app)
    val shared = SharedPrefHelper.getInstance(App.app)

    private val _currentLevels = MutableLiveData(localDB.getLevelDao().getLevelByCategory(categoryName))
    val currentLevels: LiveData<MutableList<uz.itschool.geo
        .localDatabase.entity.Level>> = _currentLevels


}