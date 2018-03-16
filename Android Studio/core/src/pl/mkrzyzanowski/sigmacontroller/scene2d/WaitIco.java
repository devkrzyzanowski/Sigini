package pl.mkrzyzanowski.sigmacontroller.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

import pl.mkrzyzanowski.sigmacontroller.AppConfig;
import pl.mkrzyzanowski.sigmacontroller.assets.RegionNames;

/**
 * Created by Michał on 2017-12-03.
 */

public class WaitIco extends Group {
    TextureAtlas textureAtlas;
    Img ring3, ring2, ring1;
    private Array<Img> imgArray;

    public WaitIco(TextureAtlas textureAtlas){
        this.textureAtlas = textureAtlas;
        imgArray = new Array<Img>();

        ring3 = new Img(textureAtlas.findRegion(RegionNames.LOAD_RING_3), 2.4f);
        ring2 = new Img(textureAtlas.findRegion(RegionNames.LOAD_RING_2), 3.6f);
        ring1 = new Img(textureAtlas.findRegion(RegionNames.LOAD_RING_1), 4.8f);
        imgArray.add(ring1);
        imgArray.add(ring2);
        imgArray.add(ring3);

        this.addActor(ring1);
        this.addActor(ring2);
        this.addActor(ring3);
    }

    public class Img extends Actor{
        TextureRegion textureRegion;
        private float rotateSpeed;
        public Img(TextureRegion textureRegion, float rotateSpeed){
            this.textureRegion = textureRegion;
            this.rotateSpeed = rotateSpeed;
            this.setSize(textureRegion.getRegionWidth(),textureRegion.getRegionHeight());
            this.setScale(0.7f);
            this.setPosition(AppConfig.WIDTH/2f - this.getWidth()/2f, AppConfig.HEIGHT/2f - this.getHeight()/2f + 300);
            this.setOrigin(this.getWidth()/2f, this.getHeight()/2f);

        }

        public TextureRegion getTexture() {
            return textureRegion;
        }

        @Override
        public void act(float delta) {
            this.rotateBy(-rotateSpeed);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        for (Img i : imgArray) {
            batch.draw(i.getTexture(), i.getX(), i.getY(),
                    i.getOriginX(), i.getOriginY(),
                    i.getWidth(), i.getHeight(),
                    i.getScaleX(), i.getScaleY(),
                    i.getRotation());
        }
    }

}
