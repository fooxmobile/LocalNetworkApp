package ru.fooxer.localnetworkapp.ui.main

import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import ru.fooxer.localnetworkapp.R
import ru.fooxer.localnetworkapp.ui.main.interfaces.MainView

class MainFragment : Fragment(), MainView {



    private lateinit var btnCreateConnection: Button
    private lateinit var btnConnectTo: Button
    private lateinit var spinnerConnType: Spinner


    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)

    }

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCreateConnection = view.findViewById(R.id.btnCreateConn)
        btnCreateConnection.setOnClickListener {
            presenter.onCreateConnectionClick()
        }

        btnConnectTo = view.findViewById(R.id.btnFindPeers)
        btnConnectTo.setOnClickListener {
            presenter.onConnectToClick()
        }

        spinnerConnType = view.findViewById(R.id.spinnerConnectionType)


    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }


}