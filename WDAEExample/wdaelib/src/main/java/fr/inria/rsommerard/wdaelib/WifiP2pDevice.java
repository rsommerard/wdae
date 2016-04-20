package fr.inria.rsommerard.wdaelib;

public class WifiP2pDevice {

    public String deviceName = "";
    public String deviceAddress = "";

    public WifiP2pDevice(String name, String address) {
        deviceAddress = address;
        deviceName = name;
    }

    public String toString() {
        return "[deviceName: " + deviceName + ", deviceAddress: " + deviceAddress + "]";
    }
}
