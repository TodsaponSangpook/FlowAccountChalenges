package com.todsapon.flowaccountchalenges

import android.app.Application
import com.todsapon.flowaccountchalenges.di.categoriesModule
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(categoriesModule)
        }
    }
}