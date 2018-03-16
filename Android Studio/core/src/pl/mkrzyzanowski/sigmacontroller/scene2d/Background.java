package pl.mkrzyzanowski.sigmacontroller.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

import pl.mkrzyzanowski.sigmacontroller.AppConfig;
import pl.mkrzyzanowski.sigmacontroller.assets.RegionNames;

/**
 * Created by Michał on 2017-12-03.
 */

public class Background extends Group {
    private TextureRegion background;
    private TextureAtlas textureAtlas;

    public Background(TextureAtlas textureAtlas, int i){
        this.textureAtlas = textureAtlas;
        if (i == 1) {
            background = textureAtlas.findRegion(RegionNames.BACKGROUND2);
        } else {
            background = textureAtlas.findRegion(RegionNames.BACKGROUND);
        }
        setHeight(AppConfig.HEIGHT);
        setWidth(AppConfig.WIDTH);

    }

    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.WHITE);
        batch.draw(background, getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation());
    }
}


