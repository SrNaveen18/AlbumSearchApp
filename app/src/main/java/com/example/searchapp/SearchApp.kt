package com.example.searchapp

import android.app.Application
import com.example.searchapp.di.viewModelModule
import com.example.searchapp.di.networkModule
import com.example.searchapp.di.repositoryModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class SearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            loadKoinModules(listOf(viewModelModule,networkModule,repositoryModule))
        }
    }
}