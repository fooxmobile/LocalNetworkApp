package ru.fooxer.localnetworkapp.ui.main.interfaces

import ru.fooxer.localnetworkapp.utils.connection.ConnectionOption

interface MainView {

    fun openDiscoverListActivity(connectionType: String)
    fun openNetworkMasterActivity(connectionType: String)
    fun showTypeDescription(s:String?)
    fun setSpinnerOptions(array: Array<String>)
}