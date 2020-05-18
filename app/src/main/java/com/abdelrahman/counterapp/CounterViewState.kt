package com.abdelrahman.counterapp

data class CounterViewState(val counterText: String)

sealed class CounterViewEvent {
    object ScreenLoadEvent : CounterViewEvent()
    object CounterIncrement : CounterViewEvent()
    object CounterDecrement : CounterViewEvent()
}

sealed class CounterViewEffect {
    object LessThanZero: CounterViewEffect()
}

