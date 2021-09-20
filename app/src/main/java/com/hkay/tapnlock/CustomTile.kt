package com.hkay.tapnlock

import android.content.Intent
import android.service.quicksettings.TileService


class CustomTile: TileService() {
    private val runnable: Runnable? = null
    override fun onClick() {
        if(!isLocked) {
            startMainActivity()
        }
        else {
            if (!MainActivity().permissionGranted) unlockAndRun(runnable)
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivityAndCollapse(intent)
    }

    override fun onTileRemoved() {
        super.onTileRemoved()
    }

    override fun onTileAdded() {
        super.onTileAdded()
    }

    override fun onStartListening() {
        super.onStartListening()
    }

    override fun onStopListening() {
        super.onStopListening()
    }
}