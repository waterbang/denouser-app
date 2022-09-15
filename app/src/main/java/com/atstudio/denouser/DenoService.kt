package com.atstudio.denouser


import android.app.IntentService
import android.content.Intent
import android.util.Log


class DenoService : IntentService("DenoService") {

    companion object {
        init {
            // 加载rust编译的so
            System.loadLibrary("denouser")
        }
    }

    external fun stringFromRustJNI(): String
    external fun runTest()


    override fun onHandleIntent(p0: Intent?) {
        Log.d(TAG, "onCreate: ${stringFromRustJNI()}")
        runTest()
    }
}
