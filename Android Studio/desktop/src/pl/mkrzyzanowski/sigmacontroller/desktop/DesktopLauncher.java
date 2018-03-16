package pl.mkrzyzanowski.sigmacontroller.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import pl.mkrzyzanowski.sigmacontroller.AppConfig;
import pl.mkrzyzanowski.sigmacontroller.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("user.name", "UserName");
		config.width = (int) AppConfig.WIDTH/2;
		config.height = (int) AppConfig.HEIGHT/2;
		new LwjglApplication(new MainClass(), config);
	}
}
