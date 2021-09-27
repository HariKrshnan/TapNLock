package com.hkay.tapnlock

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils.SimpleStringSplitter
import android.widget.Toast


class MainActivity : Activity() {
    var permissionGranted = false
    private fun checkPermission(context: Context): Boolean {
        var string: String? = null
        val str = packageName + "/" + ActionService::class.java.canonicalName
        val i: Int = try {
            Settings.Secure.getInt(context.applicationContext.contentResolver, "accessibility_enabled")
        } catch (unused: SettingNotFoundException) {
            0
        }
        val splitString = SimpleStringSplitter(':')
        if (i == 1 && Settings.Secure.getString(context.applicationContext.contentResolver, "enabled_accessibility_services").also { string = it } != null) {
            splitString.setString(string)
            while (splitString.hasNext()) {
                if (splitString.next().equals(str, ignoreCase = true)) {
                    permissionGranted = true
                    return true
                }
            }
        }
        permissionGranted = false
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lockScreen()
    }

     private fun lockScreen() {
        if (!checkPermission(applicationContext)) {
            Toast.makeText(applicationContext, R.string.user_guide, Toast.LENGTH_SHORT).show()
            startActivity(Intent("android.settings.ACCESSIBILITY_SETTINGS"))
        } else {
            ActionService.init()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        if (isTaskRoot) {
            finish()
        }
    }

}