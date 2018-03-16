package pl.mkrzyzanowski.sigmacontroller.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.mkrzyzanowski.sigmacontroller.MainClass;
import pl.mkrzyzanowski.sigmacontroller.assets.AssetDescriptors;
import pl.mkrzyzanowski.sigmacontroller.scene2d.Background;
import pl.mkrzyzanowski.sigmacontroller.scene2d.WaitIco;
import pl.mkrzyzanowski.sigmacontroller.util.IActivityRequestHandler;

/**
 * Created by Michał on 2017-12-03.
 */

class WaitWifiScreen extends BaseScreen {

    private final MainClass mainClass;
    private final AssetManager assetManager;
    private IActivityRequestHandler requestHandler;
    private SelectBox<String> wifiSelectBox;
    private TextField passwordField;
    private TextButton connect;
    private ImageButton search, check;
    private CheckBox savePassword, autoLogin;
    private boolean autoLog = false;

    WaitWifiScreen(MainClass mainClass){
        super(mainClass);
        this.mainClass = mainClass;
        assetManager = mainClass.getAssetManager();
        requestHandler = mainClass.getRequestHandler();
    }

    public void render(float delta) {
        super.render(delta);
    }

    protected Group createUi() {
        Group group = new Group();
        TextureAtlas uiAtlas = assetManager.get(AssetDescriptors.UI_ATLAS);
        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);
        Background background = new Background(uiAtlas, 1);
        WaitIco waitIco = new WaitIco(uiAtlas);
        Label label = new Label("test", uiSkin);
        label.setPosition(5, 1200);
        Label label2 = new Label("Sigini 0.4.1", uiSkin);
        label2.setPosition(5, 5);

        Label select = new Label("Wybierz urzadzenie:", uiSkin);
        select.setPosition(65, 750);
        wifiSelectBox = new SelectBox<String>(uiSkin);
        wifiSelectBox.setPosition(65, 650);
        wifiSelectBox.setSize(440, 100);

        if(Gdx.app.getType() == Application.ApplicationType.Android) {
            wifiSelectBox.setItems(requestHandler.getWifiList());
        } else {
            wifiSelectBox.setItems("PC");
        }
        Label pass = new Label("Podaj haslo:", uiSkin);
        pass.setPosition(65, 550);
        passwordField = new TextField("", uiSkin);
        passwordField.setPosition(65, 450);
        passwordField.setSize(440, 100);
        passwordField.setPasswordCharacter((char) 0x23);
        passwordField.setPasswordMode(true);

        search = new ImageButton(uiSkin, "search");
        search.setPosition(505, 650);
        search.setSize(150, 100);

        check = new ImageButton(uiSkin, "eye");
        check.setPosition(505, 450);
        check.setSize(150, 100);

        savePassword = new CheckBox("Zapisz haslo", uiSkin);
        savePassword.setPosition(65, 360);

        autoLogin = new CheckBox("Zapamietaj", uiSkin);
        autoLogin.setPosition(65, 280);

        connect = new TextButton("Polacz", uiSkin);
        connect.setPosition(65, 120);
        connect.setSize(590, 140);

        initListeners();
        group.addActor(background);
        group.addActor(waitIco);
        group.addActor(label2);
        group.addActor(wifiSelectBox);
        group.addActor(passwordField);
        group.addActor(select);
        group.addActor(pass);
        group.addActor(search);
        group.addActor(check);
        group.addActor(connect);
        group.addActor(savePassword);
        //group.addActor(autoLogin);
        if(Gdx.app.getType() == Application.ApplicationType.Android) {
            if (!requestHandler.isWifiEnabled()) {
                requestHandler.setWifiState(true);
            }
        }
        autoLog = mainClass.getPREFS().getBoolean("autoLogin");
        checkSavedPassword(wifiSelectBox.getSelected());
        return group;
    }

    public void connect(String selected, String text){
        if(Gdx.app.getType() == Application.ApplicationType.Android) {
            requestHandler.connectToWifi(selected, text);
            if (savePassword.isChecked()) {
                mainClass.getPREFS().putString(wifiSelectBox.getSelected(), passwordField.getText());
                mainClass.getPREFS().flush();
            }
        }
        mainClass.setScreen(new AppScreen(mainClass));
    }

    private void initListeners() {
        connect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                connect(wifiSelectBox.getSelected(), passwordField.getText());
            }
        });
        search.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                wifiSelectBox.setItems(requestHandler.getWifiList());
            }
        });
        wifiSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                checkSavedPassword(wifiSelectBox.getSelected());
            }
        });

        wifiSelectBox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                checkSavedPassword(wifiSelectBox.getSelected());
            }
        });

        autoLogin.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainClass.getPREFS().putBoolean("autoLogin", autoLogin.isChecked());
                mainClass.getPREFS().flush();
            }
        });

        check.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                passwordField.setPasswordMode(false);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                passwordField.setPasswordMode(true);
                super.touchUp(event, x, y, pointer, button);
            }
        });

    }

    private void checkSavedPassword(String selected) {
        passwordField.setText(mainClass.getPREFS().getString(selected));
    }
}
