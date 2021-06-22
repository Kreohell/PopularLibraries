package ru.geekbrains.popularlibraries

import android.app.Application
import ru.geekbrains.popularlibraries.injection.AppComponent
import ru.geekbrains.popularlibraries.injection.AppModule
import ru.geekbrains.popularlibraries.injection.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}