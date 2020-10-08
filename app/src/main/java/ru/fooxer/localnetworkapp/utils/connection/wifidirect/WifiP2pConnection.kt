package ru.fooxer.localnetworkapp.utils.connection.wifidirect

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import androidx.core.app.ActivityCompat
import ru.fooxer.localnetworkapp.utils.connection.ConnectionType
import ru.fooxer.localnetworkapp.utils.connection.wifidirect.WifiDirectManager.channel
import ru.fooxer.localnetworkapp.utils.connection.wifidirect.WifiDirectManager.manager


/*
 * Created by fooxer on 07.10.2020
 */

class WifiP2pConnection(

) : ConnectionType() {


    override val id: Int
        get() = 1

    override val name: String
        get() = "Wi-Fi Direct"
    override val description: String
        get() = TODO("Not yet implemented")
    override val peers: MutableList<WifiP2pDevice>
        get() = TODO("Not yet implemented")

    private val peerListListener = WifiP2pManager.PeerListListener { peerList ->
        val refreshedPeers = peerList.deviceList
        if (refreshedPeers != peers) {
            peers.clear()
            peers.addAll(refreshedPeers)

            //TODO: Notify UI (listview etc) of the changes.
        }
        if (peers.isEmpty()) {
            Log.d("M_MainActivity:", "No devices found")
            /*
             * In Kotlin, the return@label syntax is used for specifying
             * which function among several nested ones this statement returns from.
             */
            return@PeerListListener

        }
    }

    override fun discoverPeers(context: Context) {

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            manager?.discoverPeers(channel, object: WifiP2pManager.ActionListener {
                override fun onSuccess() {
                    // TODO: Show list of discovered peers
                    // If discovery process succeeds and detects peers
                    // the system broadcasts the WIFI_P2P_PEERS_CHANGED_ACTION

                }

                override fun onFailure(errorCode: Int) {
                    // TODO: Show error message
                }
            })
        }
    override fun connectTo() {
        TODO("Not yet implemented")
    }
    }

