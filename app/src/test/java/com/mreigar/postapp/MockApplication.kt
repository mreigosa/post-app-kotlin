package com.mreigar.postapp

import android.app.Application
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

class MockApplication : Application()

@RunWith(RobolectricTestRunner::class)
@Config(application = MockApplication::class)
abstract class AppRobolectricTestRunner : AutoCloseKoinTest() {
    @Before
    open fun before() {
    }

    @After
    open fun after() {
    }
}