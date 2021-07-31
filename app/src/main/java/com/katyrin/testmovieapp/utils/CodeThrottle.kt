package com.katyrin.testmovieapp.utils

class CodeThrottle {
    private companion object {
        const val MIN_INTERVAL = 250
    }
    private var lastEventTime = System.currentTimeMillis()

    fun throttle(code: () -> Unit) {
        val eventTime = System.currentTimeMillis()
        if (eventTime - lastEventTime > MIN_INTERVAL) {
            lastEventTime = eventTime
            code()
        }
    }
}