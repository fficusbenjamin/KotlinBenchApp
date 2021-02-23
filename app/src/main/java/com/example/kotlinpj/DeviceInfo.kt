package com.example.kotlinpj

import android.os.Build

object DeviceInfo {
    val manufacturer = capitalize(Build.MANUFACTURER)
    val model = if (Build.MODEL.startsWith(manufacturer)) Build.MODEL.replace(manufacturer, "") else Build.MODEL
    val fullDeviceName: String
        get() = manufacturer + " " + model

    private fun capitalize(s: String?): String {
        if (s == null || s.length == 0) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            Character.toUpperCase(first).toString() + s.substring(1)
        }
    }
}