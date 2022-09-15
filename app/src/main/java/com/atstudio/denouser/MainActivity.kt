package com.atstudio.denouser

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.atstudio.denouser.ui.theme.DenoUserTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG = "mylog"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DenoUserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        Thread {
            Log.d(TAG, "onCreate: ${stringFromRustJNI()}")
            runTest()
        }.start()
    }

    external fun stringFromRustJNI(): String
    external fun runTest()

    companion object {
        init {
            System.loadLibrary("denouser")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DenoUserTheme {
        Greeting("Android")
    }
}
