package com.mreigar.postapp.espresso.utils

import android.app.Application
import android.content.Context
import androidx.test.espresso.IdlingRegistry
import androidx.test.runner.AndroidJUnitRunner
import com.mreigar.data.injection.DataModules
import com.mreigar.domain.executor.Invoker
import com.mreigar.domain.executor.UseCaseInvoker
import com.mreigar.domain.injection.DomainModules
import com.mreigar.localstorage.injection.LocalStorageModules
import com.mreigar.network.injection.NetworkModules
import com.mreigar.postapp.espresso.CounterIdleResource
import com.mreigar.postapp.injection.AppModules
import org.junit.After
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

open class PostAppTestRunner : AndroidJUnitRunner() {

    private val counter = CounterIdleResource("Espresso")

    @Before
    fun initIdleResource() {
        val mockModules = module {
            factory<Invoker>(override = true) { UseCaseInvoker(idleNotifier = counter) }
        }

        loadKoinModules(listOf(mockModules))
        activateWaitForInvoker()
    }

    @After
    fun shutDownIdleResource() {
        disableWaitForInvoker()
    }

    fun activateWaitForInvoker() {
        IdlingRegistry.getInstance().register(counter)
    }

    fun disableWaitForInvoker() {
        IdlingRegistry.getInstance().unregister(counter)
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
                LocalStorageModules.databaseModule,
                NetworkModules.networkModule,
                DataModules.repositoryModule,
                DomainModules.useCaseModule,
                AppModules.presentationModules
            )
        }
    }

}