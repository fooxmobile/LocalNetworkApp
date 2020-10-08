package ru.fooxer.localnetworkapp.utils.connection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter


/*
 * Created by fooxer on 07.10.2020
 */

abstract class ConnectionType {
    abstract val id: Int
    abstract val name: String
    abstract val description: String
    //abstract val receiver: BroadcastReceiver
    abstract val peers: MutableList<*>
    //val intentFilter  = IntentFilter()

    abstract fun connectTo()
    abstract fun discoverPeers(context: Context)
}