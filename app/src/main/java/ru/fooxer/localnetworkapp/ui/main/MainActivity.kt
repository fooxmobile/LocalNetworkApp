package ru.fooxer.localnetworkapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import ru.fooxer.localnetworkapp.R
import ru.fooxer.localnetworkapp.ui.deviceslist.DiscoveredListActivity
import ru.fooxer.localnetworkapp.ui.main.interfaces.MainView
import ru.fooxer.localnetworkapp.ui.networkmaster.NetworkMasterActivity
import ru.fooxer.localnetworkapp.utils.connection.ConnectionOption

class MainActivity : AppCompatActivity(), MainView {


    val TAG: String = "MainActivity"
    val CONNECTION_TYPE = "CONNECTION_TYPE"

    private val presenter = MainPresenter(this)

    private lateinit var btnCreateConnection: Button
    private lateinit var btnConnectTo: Button
    private lateinit var spinnerConnType: Spinner
    private lateinit var tvConnectionDescription: TextView
    private lateinit var spinnerAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        btnCreateConnection = findViewById(R.id.btnCreateConn)
        btnCreateConnection.setOnClickListener {
            presenter.onCreateConnectionClick()
        }

        btnConnectTo = findViewById(R.id.btnFindPeers)
        btnConnectTo.setOnClickListener {
            presenter.onConnectToClick()
        }

        spinnerConnType = findViewById(R.id.spinnerConnectionType)
        presenter.onSpinnerBind()
        spinnerConnType.adapter = spinnerAdapter
        spinnerConnType.setOnItemClickListener { _, _, _, _ ->

            presenter.onSpinnerItemSelected(spinnerConnType.selectedItem.toString())
        }


        tvConnectionDescription = findViewById(R.id.tvConnectionDescr)

        }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun openDiscoverListActivity(connectionType: String) {
        val intent = Intent(this, DiscoveredListActivity::class.java).apply {
            putExtra(CONNECTION_TYPE, connectionType)
        }
        startActivity(intent)
    }

    override fun openNetworkMasterActivity(connectionType: String) {
        val intent = Intent(this, NetworkMasterActivity::class.java).apply {
            putExtra(CONNECTION_TYPE, connectionType)
        }
        startActivity(intent)
    }


    override fun showTypeDescription(s: String?) {
        tvConnectionDescription.text = s
    }

    override fun setSpinnerOptions(array: Array<String>) {
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, array)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }


}