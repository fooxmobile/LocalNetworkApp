package ru.fooxer.localnetworkapp.utils.connection

enum class ConnectionOption (val title: String, val description: String) {

    WIFIP2P("Wi-Fi Direct", "Creates a local network between devices"),
    NEARBY("Nearby Connection Api", " Connections between devices are high-bandwidth, low-latency, and fully encrypted to enable fast, secure data transfers");

}