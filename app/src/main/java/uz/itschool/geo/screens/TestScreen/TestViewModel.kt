package uz.itschool.geo.screens.TestScreen

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import uz.itschool.geo.localDatabase.entity.Country
import uz.itschool.geo.navigation.Screens
import kotlin.math.log

class TestViewModel(levelName: String,): ViewModel() {
    private val model = TestModel(levelName)
    private var timer: CountDownTimer? = null
    var questionType: String = ""
    var answerType: String = ""

    var thisLevel = model.getLevel()

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

    private var _answersState = MutableLiveData(mutableListOf(true, true, true, true))
    val answersState: LiveData<MutableList<Boolean>> = _answersState

    private var _score = MutableLiveData(0)
    var score: LiveData<Int> = _score

//    private var _scoreProgress = MutableLiveData((_score.value!! /
//            thisLevel.maxQuestion).toFloat())
//    var scoreProgress: LiveData<Float> = _scoreProgress

    private var _lives = MutableLiveData(3)
    val lives: LiveData<Int> = _lives

    private var tempAnswers = model.getCountryList()

    private val listSize = _countries.value!!.size

    fun getStringQuestion():String{
        var q = ""
        when(questionType){
            "capital"->{
                q = _currentQuestion.value!!.capital
            }
            "country"->{
                q = _currentQuestion.value!!.name
            }
        }
        return q
    }


    fun getImgQuestion():Int{
        return _currentQuestion.value!!.flag
    }



    fun checkQuestion(index: Int){
        val country = _answers.value!![index]
         if (country == _currentQuestion.value!!){
            _score.value = _score.value!! + 1
            stopTimer()
            nextQuestion()
            updateAnswers()
            startTimer()
        }else{
            _lives.value = _lives.value!! -1
            _answersState.value!![index] = false
            if (_lives.value == 0){
                _isGameFinished.value = true
                _isWon.value = false
            }
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
            _answersState.value = mutableListOf(true, true, true, true)
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
            }
        }.start()
    }

    fun stopTimer(){
        timer?.cancel()
    }

    fun updateLevel(){
        if (_score.value!!>thisLevel.score){
            thisLevel.score = _score.value!!
        }
        model.updateLevel(thisLevel)
        if((score.value!!/thisLevel.maxQuestion)*100>=70){
            model.unlockLevel(thisLevel.id+1)
        }

    }

    fun finishGame(navController: NavController){
        updateLevel()
        navController.navigate(Screens.Result.route)
    }

    init {
        randomiseList()
        updateAnswers()
    }
}