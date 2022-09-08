package com.example.dokka.weakref

expect class WeakReference<T : Any> internal constructor(referred: T) {
    fun get(): T?
}
