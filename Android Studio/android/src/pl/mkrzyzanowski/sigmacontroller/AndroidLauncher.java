package pl.mkrzyzanowski.sigmacontroller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.utils.Array;

import java.util.List;

import pl.mkrzyzanowski.sigmacontroller.util.IActivityRequestHandler;


public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler{

	private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 1001;
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		initialize(new MainClass(this), config);
	}

	@Override
	public boolean isWifiEnabled() {
		ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		Boolean status = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		return status;
	}

	@Override
	public void setWifiState(boolean enabled) {
		WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
		wifiManager.setWifiEnabled(enabled);
	}

	@Override
	public String WifiSSID() {
		WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		String ssid = wifiInfo.getSSID();
		return ssid;
	}

	public void connectToWifi(String SSID, String password) {
		WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
		WifiConfiguration configuration = new WifiConfiguration();
		configuration.SSID = "\"" + SSID + "\"";
		configuration.preSharedKey = "\"" + password + "\"";
		configuration.priority = 20;

		List<WifiConfiguration> wifiConfigurations = wifiManager.getConfiguredNetworks();
		for (WifiConfiguration c : wifiConfigurations){
			if (c.SSID != null && c.SSID.equals("\"" + SSID + "\"")){
				wifiManager.removeNetwork(c.networkId);
			}
		}
		int id = wifiManager.addNetwork(configuration);
		wifiManager.disconnect();
		wifiManager.enableNetwork(id, true);
		wifiManager.reconnect();
	}


	public void SetWifi(boolean enabled) {
		WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
		wifiManager.setWifiEnabled(enabled);
	}

	@Override
	public void disconnectWifi() {
		WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
		wifiManager.disconnect();
	}

	public Array<String> getWifiList() {
		Array<String> stringArray = new Array<>();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
			return stringArray;
		} else {
		WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
		List<ScanResult> list = wifiManager.getScanResults();
		for (ScanResult i : list) {
			if (i.SSID != null && i.SSID.length() > 6){
				if (i.SSID.contains("Sigma")){
					stringArray.add(i.SSID);
				}
			}
		}
		return stringArray;
	}
	}
}
