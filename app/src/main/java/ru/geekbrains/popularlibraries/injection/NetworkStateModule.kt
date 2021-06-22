package ru.geekbrains.popularlibraries.injection

import dagger.Binds
import dagger.Module
import ru.geekbrains.popularlibraries.network.AndroidNetworkStatus
import ru.geekbrains.popularlibraries.network.INetworkStatus
import javax.inject.Singleton

@Module
interface NetworkStateModule {
    @Singleton
    @Binds
    fun networkStatus(androidNetworkStatus: AndroidNetworkStatus): INetworkStatus
}