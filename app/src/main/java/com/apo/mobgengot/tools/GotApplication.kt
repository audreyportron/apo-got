package com.apo.mobgengot.tools

import android.app.Application
import com.apo.mobgengot.tools.di.networkModule
import com.apo.mobgengot.tools.di.repositoryModule
import com.apo.mobgengot.tools.di.roomModule
import com.apo.mobgengot.tools.di.serviceModule
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.android.ext.android.startKoin

class GotApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppSchedulers.init(mainThread = AndroidSchedulers.mainThread())
        startKoin(
            androidContext = this,
            modules = listOf(
                roomModule,
                networkModule,
                repositoryModule,
                serviceModule
            )
        )
    }
}