package com.abdelrahman.counterapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.Observer
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import com.abdelrahman.counterapp.CounterViewEvent.CounterDecrement
import com.abdelrahman.counterapp.CounterViewEvent.CounterIncrement
import com.abdelrahman.counterapp.CounterViewEvent.ScreenLoadEvent

class CounterActivity : AppCompatActivity() {

    private val model by viewModels<CounterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.viewState.observe(this, Observer { state ->
            setContent {
                MaterialTheme {
                    MyApp {
                        MyScreenContent(count = state.counterText, model = model)
                    }
                }
            }
        })

//
//        model.viewEffects.observe(this, EventObserver {
//            Snackbar.make(this, R.string.acticity_main_unable_to_decrement, Snackbar.LENGTH_SHORT)
//                .show()
//        })
    }

    override fun onResume() {
        super.onResume()
        model.processEvents(ScreenLoadEvent)
    }
}

@Composable
fun MyApp(content: @Composable() () -> Unit) {
    Surface() {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun myDefaultPreview() {
    MyApp {
        MyScreenContent(model = CounterViewModel())
    }
}

@Composable
fun MyScreenContent(count: String = "0", model: CounterViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            horizontalGravity = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Count: $count")
        }

        Row {
            Button(onClick = {
                model.processEvents(CounterIncrement)
            }) {
                Text(text = "+")
            }
            Divider(modifier = Modifier.weight(1f).fillMaxWidth())
            Button(onClick = {
                model.processEvents(CounterDecrement)
            }) {
                Text(text = "-")
            }
        }
    }
}