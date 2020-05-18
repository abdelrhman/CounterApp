package com.abdelrahman.counterapp

import com.abdelrahman.counterapp.CounterViewEffect.LessThanZero
import com.abdelrahman.counterapp.CounterViewEvent.CounterDecrement
import com.abdelrahman.counterapp.CounterViewEvent.CounterIncrement
import io.kotest.matchers.string.shouldBeEqualIgnoringCase
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class CounterViewModelTest {

    private lateinit var counterViewModel: CounterViewModel

    @BeforeEach
    fun setupViewModel() {
        counterViewModel = CounterViewModel()
    }

    @Test
    fun `initial app state should have counterText = 0`(){
        counterViewModel.viewState.value?.counterText shouldBeEqualIgnoringCase "0"
    }


    @Test
    fun `increment app state when CounterIncrement is received`(){
        counterViewModel.processEvents(CounterIncrement)
        counterViewModel.viewState.value?.counterText shouldBeEqualIgnoringCase "1"
    }

    @Test
    fun `decrement app state when CounterDecrement is received`(){
        counterViewModel.processEvents(CounterIncrement)
        counterViewModel.processEvents(CounterIncrement)
        counterViewModel.viewState.value?.counterText shouldBeEqualIgnoringCase "2"
        counterViewModel.processEvents(CounterDecrement)
        counterViewModel.viewState.value?.counterText shouldBeEqualIgnoringCase "1"
    }

    @Test
    fun `given counter state is 0 when CounterDecrement invoke view effect `(){
        counterViewModel.processEvents(CounterDecrement)
        counterViewModel.viewState.value?.counterText shouldBeEqualIgnoringCase "0"
        counterViewModel.viewEffects.value?.peekContent() shouldBeSameInstanceAs  LessThanZero
    }



}