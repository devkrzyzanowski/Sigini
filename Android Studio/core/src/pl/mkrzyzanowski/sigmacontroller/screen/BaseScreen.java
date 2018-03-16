package pl.mkrzyzanowski.sigmacontroller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.mkrzyzanowski.sigmacontroller.AppConfig;
import pl.mkrzyzanowski.sigmacontroller.MainClass;
import pl.mkrzyzanowski.sigmacontroller.util.GdxUtils;

/**
 * Created by Michał on 2017-12-02.
 */

public abstract class BaseScreen extends ScreenAdapter {
    protected final MainClass mainClass;
    protected final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;

    public BaseScreen(MainClass mainClass){
        this.mainClass = mainClass;
        assetManager = mainClass.getAssetManager();
    }

    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        viewport = new FitViewport(AppConfig.WIDTH, AppConfig.HEIGHT, camera);
        stage = new Stage(viewport, mainClass.getBatch());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(createUi());
        stage.setDebugAll(AppConfig.DEBUG);
    }

    protected abstract Group createUi();

    public void render(float delta){
        GdxUtils.clearScreen();
        stage.act();
        stage.draw();
    }

    public void dispose(){
        stage.dispose();
    }

}
