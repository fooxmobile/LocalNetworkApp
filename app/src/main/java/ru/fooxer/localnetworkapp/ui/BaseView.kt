package ru.fooxer.localnetworkapp.ui

interface BaseView {

    /*
    * This is a general method used for showing some kind of progress during
    *  background tasks, method should show progress bar and/or disable buttons
     */
    fun showProgress()

    /*
     * This method used for hiding progress information after background task finishes
     */
    fun hideProgress()

    /*
     * This method id used for showing error messages on the UI.
     * @param message - the error message to be displayed
     */
    fun showError(message: String)
}