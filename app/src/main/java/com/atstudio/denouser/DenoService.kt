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

    private external fun stringFromRustJNI()
    private external fun runTest()


    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(p0: Intent?) {
         stringFromRustJNI()
        runTest()
    }
}
