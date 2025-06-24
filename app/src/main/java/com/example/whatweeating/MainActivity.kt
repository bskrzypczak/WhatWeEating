package com.example.whatweeating

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.whatweeating.ui.navigation.Navigation
import com.example.whatweeating.ui.theme.WhatWeEatingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            splashScreen.setKeepOnScreenCondition { false }
        }

        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->

            val view = splashScreenViewProvider.view

            val scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 1.2f)
            val scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 1.2f)
            val alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)

            scaleX.duration = 1500
            scaleY.duration = 1500
            alpha.duration = 1500

            alpha.addListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator) {
                    splashScreenViewProvider.remove()
                }
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })

            scaleX.start()
            scaleY.start()
            alpha.start()
        }

        setContent {
            WhatWeEatingTheme {
                Navigation()
            }
        }
    }
}