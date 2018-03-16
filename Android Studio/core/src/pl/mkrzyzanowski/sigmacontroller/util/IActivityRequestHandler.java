package pl.mkrzyzanowski.sigmacontroller.util;


import com.badlogic.gdx.utils.Array;

/**
 * Created by Michał on 2017-12-15.
 */

public interface IActivityRequestHandler {
    boolean isWifiEnabled();
    String WifiSSID();
    void SetWifi(boolean enabled);
    Array<String> getWifiList();
    void connectToWifi(String SSID, String password);
    void setWifiState(boolean enabled);
    void disconnectWifi();
}
