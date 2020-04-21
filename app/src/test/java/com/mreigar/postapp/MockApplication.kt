package com.mreigar.postapp

import android.app.Application
import android.os.Build
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

class MockApplication : Application()

@RunWith(RobolectricTestRunner::class)
@Config(application = MockApplication::class, sdk = [Build.VERSION_CODES.P])
abstract class AppRobolectricTestRunner : AutoCloseKoinTest() {
    @Before
    open fun before() {
    }

    @After
    open fun after() {
    }
}