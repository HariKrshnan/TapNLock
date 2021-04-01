package com.hkay.tapnlock

import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.view.accessibility.AccessibilityEvent
import androidx.annotation.RequiresApi

class ActionService: AccessibilityService() {
     var isEnabled: Boolean = false
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        //empty
    }

    override fun onInterrupt() {
        //empty
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onServiceConnected() {
        isEnabled = true
//        performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
    }


}