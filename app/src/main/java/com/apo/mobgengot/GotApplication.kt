package com.apo.mobgengot

import android.app.Application
import com.apo.mobgengot.di.networkModule
import com.apo.mobgengot.di.roomModule
import org.koin.android.ext.android.startKoin

class GotApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            androidContext = this,
            modules = listOf(
                roomModule,
                networkModule
            )
        )
    }
}