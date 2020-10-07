package ru.fooxer.localnetworkapp.utils

import android.content.BroadcastReceiver
import android.content.IntentFilter


/*
 * Created by fooxer on 07.10.2020
 */

abstract class ConnectionType {
    abstract val name: String
    abstract val description: String
    abstract val receiver: BroadcastReceiver
    val intentFilter  = IntentFilter()

    abstract fun discoverPeers()
    abstract fun connectTo()
}