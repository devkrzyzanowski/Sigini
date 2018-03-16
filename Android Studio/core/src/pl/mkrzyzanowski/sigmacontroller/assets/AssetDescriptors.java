package pl.mkrzyzanowski.sigmacontroller.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Michał on 2017-12-02.
 */

public class AssetDescriptors {

    public static final AssetDescriptor<TextureAtlas> UI_ATLAS =
            new AssetDescriptor<TextureAtlas>(AssetPaths.UI_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<Skin> UI_SKIN =
            new AssetDescriptor<Skin>(AssetPaths.UI_SKIN, Skin.class);

}
