package com.mreigar.postapp.espresso.utils

import androidx.test.platform.app.InstrumentationRegistry

object EspressoTestUtils {

    fun getText(stringResourceId: Int): String {
        return InstrumentationRegistry.getInstrumentation().targetContext.getString(stringResourceId)
    }

    fun getText(stringResourceId: Int, vararg formatArgs: Any): String {
        return InstrumentationRegistry.getInstrumentation().targetContext.getString(stringResourceId, *formatArgs)
    }
}