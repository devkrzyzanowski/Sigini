package pl.mkrzyzanowski.sigmacontroller.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.mkrzyzanowski.sigmacontroller.AppConfig;
import pl.mkrzyzanowski.sigmacontroller.MainClass;
import pl.mkrzyzanowski.sigmacontroller.assets.AssetDescriptors;
import pl.mkrzyzanowski.sigmacontroller.util.GdxUtils;
import pl.mkrzyzanowski.sigmacontroller.util.IActivityRequestHandler;

/**
 * Created by Michał on 2017-12-02.
 */

public class LoadingScreen extends ScreenAdapter{

    // constants

    private static final float PROGRESS_BAR_WIDTH = AppConfig.WIDTH;
    private static final float PROGRESS_BAR_HEIGHT = 60;

    // attributes

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private float progress;
    private float waitTime = 0f;
    private boolean changeScreen;

    private final MainClass mainClass;
    private final AssetManager assetManager;




    public LoadingScreen(MainClass mainClass) {
        this.mainClass = mainClass;
        this.assetManager = mainClass.getAssetManager();
    }

    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        viewport = new FitViewport(AppConfig.WIDTH, AppConfig.HEIGHT, camera);
        renderer = new ShapeRenderer();
        assetManager.load(AssetDescriptors.UI_SKIN);
        assetManager.load(AssetDescriptors.UI_ATLAS);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        GdxUtils.clearScreen();
        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        draw();
        renderer.end();
        if (changeScreen){
            mainClass.setScreen(new WaitWifiScreen(mainClass));
        }
    }

    public void draw(){
        float progressBarX = (AppConfig.WIDTH - PROGRESS_BAR_WIDTH) / 2f;
        float progressBarY = (AppConfig.HEIGHT - PROGRESS_BAR_HEIGHT) / 2f;
        renderer.rect(progressBarX, progressBarY,
                progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT
        );
    }

    private void update(float delta) {
        progress = assetManager.getProgress();
        if (assetManager.update()){
            waitTime -= delta;

            if (waitTime <= 0){
                changeScreen = true;
            }
        }
    }

    public void dispose(){
        renderer.dispose();
        renderer = null;
    }
}
