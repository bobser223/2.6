package com.example.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    private val timerModel = TimerModel()


    private var _isTimerRunning = MutableLiveData<Boolean>(false)
    val isTimerRunning: LiveData<Boolean> get() = _isTimerRunning

    private var _timerLiveData = MutableLiveData<Long>(30L)
    val timerLiveData: LiveData<Long> get() = _timerLiveData

    private var _userSeconds = MutableLiveData<Long>(30)
    val userSeconds: LiveData<Long> get() = _userSeconds


    fun startTimer(userSeconds: Long) {
        _userSeconds.value = userSeconds
        _isTimerRunning.value = true

        updateSeconds(userSeconds)

        timerModel.start(
            _userSeconds.value ?: 30L,
            { seconds ->
                updateSeconds(seconds)
            },
            {
                _isTimerRunning.value = false
                updateSeconds(0)
            }
        )
    }

    private fun updateSeconds(seconds: Long) {
        if (_timerLiveData.value == seconds) return

        Log.d(TAG, "Timer seconds changed: $seconds")
        _timerLiveData.value = seconds
    }

    fun setTimerSeconds(seconds: Long){

    }

    fun resetTimer(){
        timerModel.stop()

        _timerLiveData.value = _userSeconds.value ?: 30L
        //_timerLiveData.value = 30L в завданні проситься так, але мій варіант (той що зверху) здається логічніше
        _isTimerRunning.value = false

    }


    override fun onCleared(){
        Log.d(TAG, "onCleared: timer stopped")
        timerModel.stop()
        super.onCleared()
    }

    private companion object {
        const val TAG = "TimerViewModel"
    }
}
