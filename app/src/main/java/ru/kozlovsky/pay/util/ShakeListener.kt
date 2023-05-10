package ru.kozlovsky.pay.util

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeListener(
    private val onShake: (Int) -> Unit,
) : SensorEventListener {

    private var shakeTimestamp: Long = 0L
    private var shakeCount: Int = 0

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        val gX = x / SensorManager.GRAVITY_EARTH
        val gY = y / SensorManager.GRAVITY_EARTH
        val gZ = z / SensorManager.GRAVITY_EARTH

        val gForce = sqrt(gX * gX + gY * gY + gZ * gZ)

        if (gForce > SHAKE_THRESHOLD_GRAVITY) {
            val now = System.currentTimeMillis()
            if (shakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                return
            }

            if (shakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                shakeCount = 0
            }

            shakeTimestamp = now
            shakeCount.inc()

            onShake.invoke(shakeCount)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // Ignored
    }

    companion object {
        private const val SHAKE_THRESHOLD_GRAVITY = 2.7F
        private const val SHAKE_SLOP_TIME_MS = 500
        private const val SHAKE_COUNT_RESET_TIME_MS = 3000
    }
}