package com.avinnikov.behance.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData

class ConnectionLiveData(context: Context) : LiveData<Boolean>() {

    private var connectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    private var networkCallback =
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network?) {
                postValue(true)
            }

            override fun onLost(network: Network?) {
                postValue(false)
            }
        }

    override fun onActive() {
        super.onActive()

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager
                .registerDefaultNetworkCallback(networkCallback)
            else -> {
                val builder = NetworkRequest.Builder()
                    .addTransportType(TRANSPORT_CELLULAR).addTransportType(TRANSPORT_WIFI)

                connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
            }
        }
    }

    override fun onInactive() {
        super.onInactive()

        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}