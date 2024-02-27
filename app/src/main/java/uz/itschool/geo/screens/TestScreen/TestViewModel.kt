package uz.itschool.geo.screens.TestScreen

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.itschool.geo.localDatabase.entity.Country

class TestViewModel(val levelName: String): ViewModel() {
    private val model = TestModel(levelName)
    val time = "20"
    private var timer: CountDownTimer? = null

    private var _countries = MutableLiveData(model.getCountryList())
    val countries: LiveData<MutableList<Country>> = _countries

    private var tempAnswers = _countries.value!!

    private var _isTimeFinished = MutableLiveData(false)
    val isTimeFinished: LiveData<Boolean> = _isTimeFinished

    private var _timeProgress = MutableLiveData(time)
    val timeProgress: LiveData<String> = _timeProgress

    private var _questionNumber = MutableLiveData(0)
    val questionNumber: LiveData<Int> = _questionNumber

    private var _currentQuestion = MutableLiveData(_countries.value!![_questionNumber.value!!])
    var currentQuestion: LiveData<Country> = _currentQuestion

    private var _answers = MutableLiveData(mutableListOf<Country>())
    var answers: LiveData<MutableList<Country>> = _answers

    private val listSize = _countries.value!!.size

    fun updateAnswers(){
        _answers.value!!.removeAll(_answers.value!!)
        tempAnswers.remove(_currentQuestion.value)
        tempAnswers.shuffle()
        _answers.value!!.add(_currentQuestion.value!!)
        _answers.value!!.add(tempAnswers[0])
        _answers.value!!.add(tempAnswers[1])
        _answers.value!!.add(tempAnswers[2])
        _answers.value!!.shuffle()

        tempAnswers = _countries.value!!
    }

    fun nextQuestion(){
        if (_questionNumber.value!! < listSize - 1){
            _questionNumber.value = _questionNumber.value!! + 1
            _currentQuestion.value = _countries.value!![_questionNumber.value!!]
        }
    }

    private fun randomiseList(){
        _countries.value!!.shuffle()
        _currentQuestion.value = countries.value!![questionNumber.value!!]
    }

    fun startTimer() {
        timer = object : CountDownTimer(21*1000L, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                _timeProgress.value = seconds.toString()
            }
            override fun onFinish() {
                _isTimeFinished.value = true
            }
        }.start()
    }

    fun stopTimer(){
        timer?.cancel()
    }

    init {
        randomiseList()
        updateAnswers()
    }


}