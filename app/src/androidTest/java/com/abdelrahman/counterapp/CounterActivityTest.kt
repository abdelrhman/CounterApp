package com.abdelrahman.counterapp

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterActivityTest{

    @Test
    fun activityInitialState_test(){
        launchActivity()
        onView(withId(R.id.btnPlus)).check(matches(isDisplayed()))
        onView(withId(R.id.btnMinus)).check(matches(isDisplayed()))
        onView(withId(R.id.tvCounter)).check(matches(isDisplayed()))
        onView(withId(R.id.tvCounter)).check(matches(withText("0")))
    }

    @Test
    fun activityClickPlusButton_test(){
        launchActivity()
        onView(withId(R.id.btnPlus)).perform(click())
        onView(withId(R.id.tvCounter)).check(matches(isDisplayed()))
        onView(withId(R.id.tvCounter)).check(matches(withText("1")))
    }

    @Test
    fun activityClickMinusButton_test(){
        launchActivity()
        onView(withId(R.id.btnPlus)).perform(click())
        onView(withId(R.id.btnPlus)).perform(click())
        onView(withId(R.id.btnMinus)).perform(click())
        onView(withId(R.id.tvCounter)).check(matches(isDisplayed()))
        onView(withId(R.id.tvCounter)).check(matches(withText("1")))
    }

    @Test
    fun activityClickMinusButton_unableToDecrement_test(){
        launchActivity()
        onView(withId(R.id.btnMinus)).perform(click())
        onView(withId(R.id.tvCounter)).check(matches(isDisplayed()))
        onView(withId(R.id.tvCounter)).check(matches(withText("0")))
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.acticity_main_unable_to_decrement)))

    }


    private fun launchActivity(): ActivityScenario<CounterActivity>? {
        return launch(CounterActivity::class.java)
    }
}