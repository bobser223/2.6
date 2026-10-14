package com.example.mvvm

import android.os.CountDownTimer

class TimerModel {

    private var timer: CountDownTimer? = null

    fun start(secsInFuture: Long, onTick: (Long) -> Unit, onFinish: () -> Unit){
        timer?.cancel()


        timer = object : CountDownTimer(secsInFuture * 1000, 1_000){
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                onFinish()
            }

        }
        timer?.start()
    }

    fun stop(){
        timer?.cancel()
        timer = null
    }
}