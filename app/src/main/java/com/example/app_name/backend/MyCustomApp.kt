package com.example.app_name.backend

import androidx.multidex.MultiDexApplication
import com.example.app_name.koinDI.retrofitModule
import com.example.app_name.koinDI.utilModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * For the starting of KoinDI
 */
class MyCustomApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyCustomApp)
            modules(
                listOf(
                    retrofitModule, utilModule
                )
            )
        }
    }
}