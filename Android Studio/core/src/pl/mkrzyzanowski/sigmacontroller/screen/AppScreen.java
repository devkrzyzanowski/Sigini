package pl.mkrzyzanowski.sigmacontroller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import pl.mkrzyzanowski.sigmacontroller.MainClass;
import pl.mkrzyzanowski.sigmacontroller.assets.AssetDescriptors;
import pl.mkrzyzanowski.sigmacontroller.enumValues.Profiles;
import pl.mkrzyzanowski.sigmacontroller.scene2d.Background;
import pl.mkrzyzanowski.sigmacontroller.util.IActivityRequestHandler;
import pl.mkrzyzanowski.sigmacontroller.util.Tcp;
import pl.mkrzyzanowski.sigmacontroller.util.Profile;

/**
 * Created by Michał on 2017-12-02.
 */

class AppScreen extends BaseScreen{

    private final MainClass mainClass;
    private final AssetManager assetManager;
    private Profile profile;
    private Tcp tcp;
    private IActivityRequestHandler requestHandler;
    private Label label;

    AppScreen(MainClass mainClass) {
        super(mainClass);
        this.mainClass = mainClass;
        assetManager = mainClass.getAssetManager();
        this.requestHandler = mainClass.getRequestHandler();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
    }

    private void update() {
        try {
            if (requestHandler.isWifiEnabled()) {
                label.setText("Polaczono : " + requestHandler.WifiSSID());
            } else {
                label.setText("WI-FI niedostepne");
            }
        } catch (NullPointerException e){
            Gdx.app.debug("requestHandler", "error");
        }
    }

    protected Group createUi() {
        Group group = new Group();
        TextureAtlas uiAtlas = assetManager.get(AssetDescriptors.UI_ATLAS);
        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);
        profile = new Profile(1, uiSkin, mainClass.getPREFS());
        Background background = new Background(uiAtlas, 2);
        group.addActor(background);
        group.addActor(profile.getGroup());

        tcp = new Tcp();
        label = new Label("", uiSkin);
        label.setPosition(5, 40);
        group.addActor(label);

        ImageButton saveButton = new ImageButton(uiSkin, "disc");
        saveButton.setPosition(260, 1180);
        saveButton.setSize(100, 100);
        group.addActor(saveButton);

        ImageButton settingsButton = new ImageButton(uiSkin, "settings");
        settingsButton.setPosition(720 - 100 - 15, 1180);
        settingsButton.setSize(100, 100);
        group.addActor(settingsButton);

        ImageButton exitButton = new ImageButton(uiSkin, "exit");
        exitButton.setPosition(720 - 200 - 30, 1180);
        exitButton.setSize(100, 100);
        group.addActor(exitButton);

        TextButton sendButton = new TextButton("wyslij", uiSkin);
        sendButton.setPosition(0, 80);
        sendButton.setSize(720, 160);
        group.addActor(sendButton);
        sendButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tcp.send(profile.getRowArray());
            }
        });

        final SelectBox<Profiles> readSelectBox = new SelectBox<Profiles>(uiSkin);
        readSelectBox.setPosition(5, 1180);
        readSelectBox.setSize(240, 100);
        readSelectBox.setItems(Profiles.values());
        group.addActor(readSelectBox);

        readSelectBox.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                    profile.setProfile(readSelectBox.getSelected().getId());
             }
        });

        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                profile.save();
            }
        });
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                requestHandler.disconnectWifi();
                mainClass.setScreen(new WaitWifiScreen(mainClass));
            }
        });
        return group;
    }
}
