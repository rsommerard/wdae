package fr.inria.rsommerard.wdaelib;

import java.util.HashMap;

public class WifiP2pDeviceList {

    private final HashMap<String, WifiP2pDevice> mDevices = new HashMap<String, WifiP2pDevice>();

    public void add(WifiP2pDevice device) {
        mDevices.put(device.deviceAddress, device);
    }

    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        for (WifiP2pDevice device : mDevices.values()) {
            sbuf.append("\n").append(device);
        }
        return sbuf.toString();
    }
}
