package uz.itschool.geo.screens.TestScreen

import androidx.lifecycle.ViewModel

class TestViewModel(val levelName: String): ViewModel() {
    private val model = TestModel(levelName)

    var countries = model.countryList.shuffle()


}