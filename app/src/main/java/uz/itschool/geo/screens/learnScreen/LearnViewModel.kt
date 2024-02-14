package uz.itschool.geo.screens.learnScreen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.itschool.geo.localDatabase.entity.Country

class LearnViewModel(context: Context): ViewModel() {
    private val learnModel = LearnModel(context)
    val countryList = learnModel.getCountryList()

    private val _showDialog = MutableLiveData(false)
    val showDialog: LiveData<Boolean> = _showDialog

    private val _chosenCountry = MutableLiveData(countryList[0])
    val chosenCountry: LiveData<Country> = _chosenCountry



    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun setChosenCountry(country: Country){
        _chosenCountry.value = country
    }



}