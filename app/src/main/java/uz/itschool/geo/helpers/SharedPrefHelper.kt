package uz.itschool.geo.helpers

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper(context: Context) {

    private val shared: SharedPreferences = context
        .getSharedPreferences("shared", Context.MODE_PRIVATE)

    private val editor = shared.edit()

    private val DB_STATE_KEY = "db_state_key"
    private val COIN_KEY = "coin_key"

    companion object{
        private var instance:SharedPrefHelper? = null
        fun getInstance(context: Context):SharedPrefHelper{
            if (instance == null){
                instance = SharedPrefHelper(context)
            }
            return instance!!
        }
    }

    fun setDBState(state: Boolean){
        editor.putBoolean(DB_STATE_KEY, state).commit()
    }

    fun getDBState(): Boolean{
        return shared.getBoolean(DB_STATE_KEY, false)
    }

    fun setCoinNumber(num: Int){
        editor.putInt(COIN_KEY, num).commit()
    }

    fun getCoinNumber(): Int{
        return shared.getInt(COIN_KEY, 0)
    }

}