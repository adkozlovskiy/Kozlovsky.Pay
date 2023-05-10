package ru.kozlovsky.pay.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtils @Inject constructor(
    @ApplicationContext private val appContext: Context
) {

    @Suppress("DEPRECATION")
    fun isNetworkConnected(): Boolean {
        val manager: ConnectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks: Array<Network> = manager.allNetworks
        var networkConnected = false

        if (networks.isNotEmpty()) {
            for (network: Network in networks) {
                val capabilities: NetworkCapabilities =
                    manager.getNetworkCapabilities(network) ?: return false

                if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    networkConnected = true
                }
            }
        }
        return networkConnected
    }
}