package com.hkay.tapnlock

import android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_LOCK_SCREEN
import android.app.Activity
import android.content.Intent
import android.provider.Settings
import android.widget.Toast


class MainActivity : Activity() {
    private val actionService = ActionService()

    override fun onStart() {
        super.onStart()
        actionService.performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)

//        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
/*
        if(!actionService.isEnabled) {
            Toast.makeText(applicationContext, "Kindly enable the accessibility service to use this app", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "Else block", Toast.LENGTH_SHORT).show()
            actionService.performGlobalAction(8)
        }
*/
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(applicationContext, "Activity Result", Toast.LENGTH_SHORT).show()
        if(!actionService.isEnabled) {
            val intent: Intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }
    }

}