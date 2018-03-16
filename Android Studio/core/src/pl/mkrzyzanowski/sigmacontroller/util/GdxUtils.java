package pl.mkrzyzanowski.sigmacontroller.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by Michał on 2017-12-02.
 */

public class GdxUtils {
    public static void clearScreen() {
        clearScreen(Color.WHITE);
    }

    private static void clearScreen(Color color) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    private GdxUtils(){
    }
}
