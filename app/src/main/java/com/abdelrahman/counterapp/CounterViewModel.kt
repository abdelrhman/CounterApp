package com.abdelrahman.counterapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdelrahman.counterapp.CounterViewEffect.*
import com.abdelrahman.counterapp.CounterViewEvent.CounterDecrement
import com.abdelrahman.counterapp.CounterViewEvent.CounterIncrement
import com.abdelrahman.counterapp.CounterViewEvent.ScreenLoadEvent

class CounterViewModel : ViewModel() {

    private val mutableViewState = MutableLiveData(CounterViewState("0"))
    val viewState: LiveData<CounterViewState>
        get() = mutableViewState

    private val mutableViewEffects = MutableLiveData<Event<CounterViewEffect>>()
    val viewEffects: LiveData<Event<CounterViewEffect>>
        get() = mutableViewEffects

    fun processEvents(event: CounterViewEvent) {
        when (event) {
            ScreenLoadEvent -> {
                // Do some work whenever the screen is loaded.
            }
            CounterIncrement -> {
                viewState.value?.let {
                    mutableViewState.postValue(CounterViewState(it.counterText.toInt().inc().toString()))
                } ?: run {
                    throw IllegalStateException("Something went very wrong")
                }
            }
            CounterDecrement -> {
                viewState.value?.let {
                    val counter = it.counterText.toInt().dec()
                    if (counter > -1) {
                        mutableViewState.postValue(CounterViewState(counter.toString()))
                    } else {
                        mutableViewEffects.value = Event(LessThanZero)
                    }
                } ?: run {
                    throw IllegalStateException("Something went very wrong")
                }
            }
        }
    }
}