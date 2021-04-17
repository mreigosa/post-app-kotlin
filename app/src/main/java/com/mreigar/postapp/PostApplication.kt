package com.mreigar.postapp

import android.app.Application
import com.mreigar.data.injection.DataModules
import com.mreigar.domain.injection.DomainModules
import com.mreigar.localstorage.injection.LocalStorageModules
import com.mreigar.network.injection.NetworkModules
import com.mreigar.presentation.injection.PresentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PostApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoinModules()
    }

    private fun initKoinModules() {
        startKoin {
            androidContext(this@PostApplication)
            modules(
                NetworkModules.networkModule,
                LocalStorageModules.databaseModule,
                DataModules.repositoryModule,
                DomainModules.useCaseModule,
                PresentationModules.presentationModules,
            )
        }
    }
}
