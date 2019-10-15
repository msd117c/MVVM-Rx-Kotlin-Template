package com.msd.mvvmtemplate.domain.connection.repository

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import com.msd.mvvmtemplate.domain.connection.model.ConnectionModel
import com.msd.mvvmtemplate.utils.Constants.Companion.DATA_CONNECTION
import com.msd.mvvmtemplate.utils.Constants.Companion.UNDEFINED
import com.msd.mvvmtemplate.utils.Constants.Companion.WIFI_CONNECTION
import javax.inject.Inject

class ConnectionLiveData @Inject constructor(
    private val context: Context
) : LiveData<ConnectionModel>() {

    override fun onActive() {
        super.onActive()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReceiver)
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.extras != null) {
                val activeNetworkInfo =
                    intent.extras?.get(ConnectivityManager.EXTRA_NETWORK_INFO) as NetworkInfo
                var connectionType = UNDEFINED
                val isConnected = activeNetworkInfo.isConnectedOrConnecting
                if (isConnected) {
                    connectionType = when (activeNetworkInfo.type) {
                        ConnectivityManager.TYPE_WIFI -> WIFI_CONNECTION
                        ConnectivityManager.TYPE_MOBILE -> DATA_CONNECTION
                        else -> UNDEFINED
                    }
                }
                postValue(ConnectionModel(connectionType, isConnected))
            }
        }
    }
}