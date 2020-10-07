package ru.fooxer.localnetworkapp.ui.main

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import ru.fooxer.localnetworkapp.R
import ru.fooxer.localnetworkapp.WifiP2pBroadcastReceiver
import ru.fooxer.localnetworkapp.ui.main.interfaces.MainView

class MainActivity : AppCompatActivity(), MainView {


    val TAG: String = "MainActivity"
    private val presenter = MainPresenter(this)

    //TODO: Create WifiDirectManager class to free activity and send it to Presenter
    private val wifiIntentFilter = IntentFilter()
    private var isWifiP2pEnabled = false

    val manager: WifiP2pManager? by lazy(LazyThreadSafetyMode.NONE) {
        getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager?
    }

    var channel: WifiP2pManager.Channel? = null
    var receiver: BroadcastReceiver? = null



    private val peers = mutableListOf<WifiP2pDevice>()

    val peerListListener = WifiP2pManager.PeerListListener { peerList ->
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        //Indicates a change in the WIFI 2p2 status
//        wifiIntentFilter.apply {
//            addAction((WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION))
//
//            // Indicates a change in the list of available peers
//            addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
//
//            //Indicates the state of wifi p2p connectivity has changed
//            addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)
//
//            //Indicates this device's details have changed
//            addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
//        }

            channel = manager?.initialize(this, mainLooper,null)
            channel?.also { channel ->
                receiver = WifiP2pBroadcastReceiver(manager!!, channel, presenter)

            }
                Log.d("M_MainActivity:", "Wifi2p2 manager, channel, receiver were created")



            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
            }
        }

    override fun onResume() {
        super.onResume()
        receiver?.also{ receiver->
            registerReceiver(receiver, wifiIntentFilter)
        }
    }

    override fun onPause() {
        super.onPause()
        receiver?.also{receiver->
            unregisterReceiver(receiver)
        }
    }


    fun setWifiP2pEnabled(state: Boolean) {
        isWifiP2pEnabled = state
    }

    fun discoverPeers() {
        if (ActivityCompat.checkSelfPermission(
                this,
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



}