package ru.fooxer.localnetworkapp.ui.main

import ru.fooxer.localnetworkapp.ui.main.interfaces.MainView


/*
 * Created by fooxer on 07.10.2020
 */

class MainPresenter constructor (
    private var view: MainView?,
) {
    fun onCreateConnectionClick() {

    }

    fun onConnectToClick() {

    }

    fun onSpinnerItemSelect() {

    }

    fun onViewDestroyed() {
        view = null
    }


    //TODO: clear activity field after activity destroyed to avoid memory leaks
}