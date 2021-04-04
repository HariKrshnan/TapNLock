package com.hkay.tapnlock

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class ActionService: AccessibilityService() {
     var isEnabled: Boolean = false

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        //empty
    }

    override fun onInterrupt() {
        //empty
    }

    override fun onServiceConnected() {
        isEnabled = true
        accService = this;
    }

    override fun onUnbind(intent: Intent): Boolean {
        accService = null;
        return super.onUnbind(intent);
    }

    private fun lockScreen() {
         performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
     }

    companion object {
        private var accService: ActionService? = null
        fun init() {
            val service: ActionService? = accService
            Log.i("init", "init method called")
            service?.lockScreen()
        }
    }

}