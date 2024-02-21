package uz.itschool.geo.screens.TestScreen

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.itschool.geo.App
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country

class TestViewModel(val levelName: String): ViewModel() {
    val localDB = AppDataBase.getInstance(App.app)
    var countryList:MutableList<Country> = localDB.getCountryDao().getByLevel(levelName)
    val time = "20"

    private var _countries = MutableLiveData(countryList)
    val countries: LiveData<MutableList<Country>> = _countries

    private var _test_countries=MutableLiveData(countryList)
    val test_country_list:LiveData<MutableList<Country>> = _test_countries

    private var _isTimeFinished = MutableLiveData(false)
    val isTimeFinished: LiveData<Boolean> = _isTimeFinished

    private var _timeProgress = MutableLiveData(time)
    val timeProgress: LiveData<String> = _timeProgress

    private var _questionNumber = MutableLiveData(0)
    val questionNumber: LiveData<Int> = _questionNumber


    private val listSize = _countries.value!!.size


    fun nextQuestion(){
        var question:Country
        if (_questionNumber.value!! < listSize - 1){
            _questionNumber.value = _questionNumber.value!! + 1
            question=_countries.value!![_questionNumber.value!!]
        }

    }



    fun randomiseList(){
        _countries.value!!.shuffle()
    }

    fun startTime() {
        val totalTime = time.toInt() * 1000L
        object : CountDownTimer(totalTime, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                _timeProgress.value = seconds.toString()
            }

            override fun onFinish() {
                _isTimeFinished.value = true
            }
        }.start()
    }


}