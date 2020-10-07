package ru.fooxer.localnetworkapp.utils.connection

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pManager
import ru.fooxer.localnetworkapp.utils.ConnectionType


/*
 * Created by fooxer on 07.10.2020
 */

class WifiP2pConnection(
    override val receiver: BroadcastReceiver
) : ConnectionType() {

    init {
        intentFilter.apply {
            addAction((WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION))

            // Indicates a change in the list of available peers
            addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)

            //Indicates the state of wifi p2p connectivity has changed
            addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)

            //Indicates this device's details have changed
            addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
        }
    }

    override fun discoverPeers() {
        TODO("Not yet implemented")
    }
}