package ru.fooxer.localnetworkapp.ui.main

import ru.fooxer.localnetworkapp.ui.main.interfaces.MainView
import ru.fooxer.localnetworkapp.utils.connection.ConnectionOption
import ru.fooxer.localnetworkapp.utils.connection.ConnectionType
import ru.fooxer.localnetworkapp.utils.connection.wifidirect.WifiP2pConnection


/*
 * Created by fooxer on 07.10.2020
 */

class MainPresenter constructor (
    private var view: MainView?,
) {

    private lateinit var connection: ConnectionType
    fun onCreateConnectionClick() {
        // connection create network
        // 1) Take connection type
        // view?.getFinalSpinnerChoice()
        // 2) create connection (group)
        // 3) open NetworkMasterActivity
        val connectionType: String = connection.name
        view?.openNetworkMasterActivity(connectionType)

    }

    fun onConnectToClick() {
        // Connectioin.discover -> Open Fragment List with founded peers
        // Take connection type
        //view?.getFinalSpinnerChoice()
        // connection discover - get peers list
        // open list activity
        val connectionType: String = connection.name
        view?.openDiscoverListActivity(connectionType)
    }

    fun onSpinnerItemSelected(option: String) {

        val o = ConnectionOption.values().find { it. title == option}
        connection = when (o) {
            ConnectionOption.WIFIP2P -> {
                WifiP2pConnection()
            }
            ConnectionOption.NEARBY -> TODO()
            null -> WifiP2pConnection()
        }
        // show type description!
        // get connection description
        view?.showTypeDescription(o?.description)
        // choose connection Type
        // set connection type
    }



    fun onViewDestroyed() {
        //clears view field after activity destroyed to avoid memory leaks
        view = null
    }

    fun onSpinnerBind() {
        val array = Array<String>(ConnectionOption.values().size, init = {

                ConnectionOption.values().get(it).title

        })
        view?.setSpinnerOptions(array)
    }




}