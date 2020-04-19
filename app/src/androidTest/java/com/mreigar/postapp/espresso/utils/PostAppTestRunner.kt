package com.mreigar.postapp.espresso.utils

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.mreigar.data.injection.DataModules
import com.mreigar.domain.injection.DomainModules
import com.mreigar.network.injection.NetworkModules
import com.mreigar.postapp.injection.AppModules
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

open class PostAppTestRunner : AndroidJUnitRunner() {

    @Before
    fun initIdleResource() {
        val mockModules = module {
        }
        loadKoinModules(listOf(mockModules))
    }

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(classLoader: ClassLoader, className: String, context: Context): Application =
        super.newApplication(classLoader, MockPostApplication::class.java.name, context)

}

class MockPostApplication : Application() {

    companion object {
        private var mInstance: MockPostApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        startKoin {
            androidContext(this@MockPostApplication)
            modules(
                NetworkModules.networkModule,
                DataModules.repositoryModule,
                DomainModules.useCaseModule,
                AppModules.presentationModules
            )
        }
    }

}