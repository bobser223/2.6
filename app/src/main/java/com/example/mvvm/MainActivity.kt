package com.example.mvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: TimerViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.timerLiveData.observe(this) { seconds ->
            binding.tvTimer.text = seconds.toString()
        }

        viewModel.isTimerRunning.observe(this) { isRunning ->
            binding.btnStart.isEnabled = !isRunning
        }

        binding.btnStart.setOnClickListener {
            val userSeconds = binding.etSeconds.text.toString().toLongOrNull()?.takeIf { it > 0 } ?: 30L

            viewModel.startTimer(userSeconds)
        }

        binding.btnReset.setOnClickListener {
            viewModel.resetTimer()
        }

    }

    private fun startTimer() {
        Log.d(TAG, "onCreate: Timer starts")

        // TODO: create code to start timer
    }

    companion object {
        val TAG = "XXXX"
    }
}