package com.example.dokka.weakref.android

import android.app.Application
import com.example.dokka.weakref.Greeting
import com.example.dokka.weakref.WeakReference

class DemoApp : Application() {
    private lateinit var greeting: Greeting

    val weakGreeting: WeakReference<Greeting>
        get() = WeakReference(greeting)

    override fun onCreate() {
        super.onCreate()

        greeting = Greeting()
    }
}
