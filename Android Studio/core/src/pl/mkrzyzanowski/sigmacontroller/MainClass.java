package pl.mkrzyzanowski.sigmacontroller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;

import pl.mkrzyzanowski.sigmacontroller.screen.LoadingScreen;
import pl.mkrzyzanowski.sigmacontroller.util.IActivityRequestHandler;

public class MainClass extends Game {

	private AssetManager assetManager;
	private SpriteBatch batch;
	private Preferences PREFS;
	private IActivityRequestHandler requestHandler;

	public MainClass(IActivityRequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
	public MainClass() {

	}

	public void create () {
		assetManager = new AssetManager();
		assetManager.getLogger().setLevel(Logger.DEBUG);
		batch = new SpriteBatch();
		PREFS = Gdx.app.getPreferences(MainClass.class.getSimpleName());
		setScreen(new LoadingScreen(this));
	}

	public AssetManager getAssetManager(){
		return assetManager;
	}

	public SpriteBatch getBatch(){
		return batch;
	}

	public void dispose () {
		batch.dispose();
	}

	public Preferences getPREFS(){
		return PREFS;
	}

	public IActivityRequestHandler getRequestHandler(){
		return requestHandler;
	}
}
