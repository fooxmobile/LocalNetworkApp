package ru.fooxer.localnetworkapp.utils.connection.wifidirect

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pManager
import android.os.Looper
import androidx.core.content.ContextCompat.getSystemService


/*
 * Created by fooxer on 08.10.2020
 */

object WifiDirectManager {

    var manager: WifiP2pManager? = null
    var channel: WifiP2pManager.Channel? = null
    var receiver: BroadcastReceiver? = null
    val intentFilter = IntentFilter()
    fun registerWifiDirectManager(context: Context) {
        manager = context.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
        channel =  manager?.initialize(context, Looper.getMainLooper(),null)
        intentFilter.apply {
            addAction((WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION))

            // Indicates a change in the list of available peers
            addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)

            //Indicates the state of wifi p2p connectivity has changed
            addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)

            //Indicates this device's details have changed
            addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
        }

        receiver = WifiP2pBroadcastReceiver(manager,channel)

    }

}