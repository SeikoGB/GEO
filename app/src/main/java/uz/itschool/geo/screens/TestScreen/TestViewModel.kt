package uz.itschool.geo.screens.TestScreen

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.itschool.geo.App
import uz.itschool.geo.localDatabase.AppDataBase
import uz.itschool.geo.localDatabase.entity.Country

class TestViewModel(val levelName: String): ViewModel() {
    private val model = TestModel(levelName)
    val time = "20"

    private var _countries = MutableLiveData(model.getCountryList())
    val countries: LiveData<MutableList<Country>> = _countries

//    private var _test_countries=MutableLiveData(countryList)
//    val test_country_list:LiveData<MutableList<Country>> = _test_countries

    private var _isTimeFinished = MutableLiveData(false)
    val isTimeFinished: LiveData<Boolean> = _isTimeFinished

    private var _timeProgress = MutableLiveData(time)
    val timeProgress: LiveData<String> = _timeProgress

    private var _questionNumber = MutableLiveData(0)
    val questionNumber: LiveData<Int> = _questionNumber

    var currentQuestion = countries.value!![questionNumber.value!!]


    private val listSize = _countries.value!!.size


    fun nextQuestion(){
        if (_questionNumber.value!! < listSize - 1){
            _questionNumber.value = _questionNumber.value!! + 1
            currentQuestion =_countries.value!![_questionNumber.value!!]
        }

    }



    private fun randomiseList(){
        _countries.value!!.shuffle()
        currentQuestion = countries.value!![questionNumber.value!!]
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


    init {
        randomiseList()
    }


}