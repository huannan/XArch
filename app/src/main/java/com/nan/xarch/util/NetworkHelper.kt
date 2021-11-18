package com.nan.xarch.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.provider.Settings
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.nan.xarch.XArchApplication

object NetworkHelper {

    /**
     * 网络状态监听
     */
    fun observerNetworkState(activity: FragmentActivity, callback: (Boolean) -> Unit) {
        val manager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                if (activity.isAlive) {
                    activity.runOnUiThread { callback(true) }
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                if (activity.isAlive) {
                    activity.runOnUiThread { callback(false) }
                }
            }
        }
        manager.requestNetwork(NetworkRequest.Builder().build(), networkCallback)

        activity.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                manager.unregisterNetworkCallback(networkCallback)
            }
        })
    }

    fun isNetworkConnect(): Boolean {
        val cm = XArchApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo?.isAvailable ?: false
    }

    fun toNetworkSetting(context: Context) {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        context.startActivity(intent)
    }
}