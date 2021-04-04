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
    private val actionService = ActionService()


    private fun checkPermission(context: Context): Boolean {
        var string: String? = null
        val str = packageName + "/" + ActionService::class.java.canonicalName
        val i: Int = try {
             Settings.Secure.getInt(context.applicationContext.contentResolver, "accessibility_enabled")
        } catch (unused: SettingNotFoundException) {
            0
        }
        val simpleStringSplitter = SimpleStringSplitter(':')
        if (i == 1 && Settings.Secure.getString(context.applicationContext.contentResolver, "enabled_accessibility_services").also { string = it } != null) {
            simpleStringSplitter.setString(string)
            while (simpleStringSplitter.hasNext()) {
                if (simpleStringSplitter.next().equals(str, ignoreCase = true)) {
                    return true
                }
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!checkPermission(applicationContext)) {
            Toast.makeText(applicationContext, " R.string.msg_info_accessibility_setting_off", Toast.LENGTH_SHORT).show()
            startActivity(Intent("android.settings.ACCESSIBILITY_SETTINGS"))
            Toast.makeText(applicationContext, "R.string.msg_info_accessibility_setting_guide", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "permission given", Toast.LENGTH_SHORT).show()
//            ActionService().init()
            actionService.init()
            finish()

        }

    }

    /* access modifiers changed from: protected */
    override fun onDestroy() {
        super.onDestroy()
    }

    /* access modifiers changed from: protected */
    override fun onPause() {
        super.onPause()
    }

    /* access modifiers changed from: protected */
    override fun onResume() {
        super.onResume()
        if (isTaskRoot) {
            finish()
        }
    }

    /* access modifiers changed from: protected */
    override fun onStop() {
        super.onStop()
    }

}