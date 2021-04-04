package com.hkay.tapnlock

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class ActionService: AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        //empty
    }

    override fun onInterrupt() {
        //empty
    }

    override fun onServiceConnected() {
        accService = this
    }

    override fun onUnbind(intent: Intent): Boolean {
        accService = null
        return super.onUnbind(intent)
    }

    private fun lockScreen() {
         performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
     }

    companion object {
        private var accService: ActionService? = null
        fun init() {
            Log.i("init", "init method called")
            accService?.lockScreen()
        }
    }

}