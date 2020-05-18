package com.abdelrahman.counterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.abdelrahman.counterapp.CounterViewEvent.*
import com.abdelrahman.counterapp.databinding.ActivityCounterBinding
import com.google.android.material.snackbar.Snackbar

class CounterActivity : AppCompatActivity() {

    private val model by viewModels<CounterViewModel>()
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.viewState.observe(this, Observer { state ->
            binding.tvCounter.text = state.counterText
        })
        model.viewEffects.observe(this, EventObserver {
            Snackbar.make(binding.root, R.string.acticity_main_unable_to_decrement, Snackbar.LENGTH_SHORT)
                .show()

        })
        binding.btnPlus.setOnClickListener {
            model.processEvents(CounterIncrement)
        }
        binding.btnMinus.setOnClickListener {
            model.processEvents(CounterDecrement)
        }
    }

    override fun onResume() {
        super.onResume()
        model.processEvents(ScreenLoadEvent)
    }
}