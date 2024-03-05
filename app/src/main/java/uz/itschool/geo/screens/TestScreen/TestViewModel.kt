package uz.itschool.geo.screens.TestScreen

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.itschool.geo.localDatabase.entity.Country

class TestViewModel(val levelName: String): ViewModel() {
    private val model = TestModel(levelName)
    private var timer: CountDownTimer? = null

    private var _countries = MutableLiveData(model.getCountryList())
    val countries: LiveData<MutableList<Country>> = _countries

    private var _isGameFinished = MutableLiveData(false)
    val isGameFinished: LiveData<Boolean> = _isGameFinished

    private var _isWon = MutableLiveData(true)
    val isWon: LiveData<Boolean> = _isWon

    private var _timeProgress = MutableLiveData("20")
    val timeProgress: LiveData<String> = _timeProgress

    private var _questionNumber = MutableLiveData(0)
    val questionNumber: LiveData<Int> = _questionNumber

    private var _currentQuestion = MutableLiveData(_countries.value!![_questionNumber.value!!])
    val currentQuestion: LiveData<Country> = _currentQuestion

    private var _answers = MutableLiveData(mutableListOf<Country>())
    var answers: LiveData<MutableList<Country>> = _answers

    private var _score = MutableLiveData(0)
    var score: LiveData<Int> = _score

    private var _lives = MutableLiveData(3)
    val lives: LiveData<Int> = _lives

    private var tempAnswers = model.getCountryList()

    private val listSize = _countries.value!!.size

    fun checkQuestion(country: Country):Boolean{
        return if (country == _currentQuestion.value){
            _score.value = _score.value!! + 1
            stopTimer()
            nextQuestion()
            updateAnswers()
            startTimer()
            true
        }else{
            _lives.value = _lives.value!! -1
            if (_lives.value == 0){
                _isGameFinished.value = true
                _isWon.value = false
            }
            false
        }
    }

    fun updateAnswers(){
        _answers.value!!.removeAll(_answers.value!!)
        tempAnswers.remove(_currentQuestion.value)
        tempAnswers.shuffle()
        _answers.value!!.add(_currentQuestion.value!!)
        _answers.value!!.add(tempAnswers[0])
        _answers.value!!.add(tempAnswers[1])
        _answers.value!!.add(tempAnswers[2])
        _answers.value!!.shuffle()

        tempAnswers = model.getCountryList()
    }

    fun nextQuestion(){
        if (_questionNumber.value!! < listSize - 1){
            _questionNumber.value = _questionNumber.value!! + 1
            _currentQuestion.value = _countries.value!![_questionNumber.value!!]
        }else{
            _isGameFinished.value = true
        }
    }

    private fun randomiseList(){
        _countries.value!!.shuffle()
        _currentQuestion.value = countries.value!![_questionNumber.value!!]
    }

    fun startTimer() {
        timer = object : CountDownTimer(21*1000L, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                _timeProgress.value = seconds.toString()
            }
            override fun onFinish() {
                _isGameFinished.value = true
                _isWon.value = false
                finishGame()
            }
        }.start()
    }

    fun stopTimer(){
        timer?.cancel()
    }

    fun finishGame(){

    }

    init {
        randomiseList()
        updateAnswers()
    }
}