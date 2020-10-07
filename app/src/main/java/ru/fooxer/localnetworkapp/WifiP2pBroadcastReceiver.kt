package ru.fooxer.localnetworkapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.p2p.WifiP2pDeviceList
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import ru.fooxer.localnetworkapp.ui.main.MainActivity
import ru.fooxer.localnetworkapp.ui.main.MainPresenter


/*
 * Created by fooxer on 06.10.2020
 */


/*
* This class is used to listen for changes
* to the System;s WiFip2p state
 */

class WifiP2pBroadcastReceiver(
    private val manager: WifiP2pManager?,
    private val channel: WifiP2pManager.Channel,
    //private val activity: MainActivity //TODO: replace with presenter
    private val presenter: MainPresenter
): BroadcastReceiver() {



    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION -> {
                // Determine if Wifi p2p mode is enabled or not
                // and alert the Activity
                val state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1)
                //activity.setWifiP2pEnabled(state == WifiP2pManager.WIFI_P2P_STATE_ENABLED)
                Log.d("M_WifiP2pReceiver:", "Wifip2p state changed action")

            }
            WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION -> {
                // Call requestPeers to get a list of current peers
                // TODO: Handle peers list
                // requestPeers is also async and can notify view when list is available
                // by onPeersAvailable callback
                //manager?.requestPeers(channel, activity.peerListListener)
            }
            WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION -> {
                // Respond to new connectioin or disconnection
            }
            WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION -> {
                // Respond to his device's wifi state changing
            }
        }
    }

}