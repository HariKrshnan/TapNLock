package com.hkay.tapnlock

import android.content.Intent
import android.service.quicksettings.TileService


class CustomTile: TileService() {
    private val runnable: Runnable? = null
    override fun onClick() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if(!isLocked) {
            startActivityAndCollapse(intent)
        }
        else {
            startActivity(intent)
            if (!MainActivity().permissionGranted) unlockAndRun(runnable)
        }
    }
}