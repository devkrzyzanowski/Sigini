package pl.mkrzyzanowski.sigmacontroller.util;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Michał on 2017-12-05.
 */

public class Profile {
    private Array<Row> rowArray;
    private Skin uiSkin;
    private Group group;
    private Preferences prefs;
    private Row row1, row2, row3, row4, row5, row6;
    private Integer id;

    public Profile(Integer id, Skin uiSkin, Preferences prefs){
        this.id = id;
        this.uiSkin = uiSkin;
        this.prefs = prefs;
        group = new Group();
        rowArray = new Array<Row>();
        rowArray.add(new Row(1, 1050, uiSkin));
        rowArray.add(row2 = new Row(2, 900, uiSkin));
        rowArray.add(row3 = new Row(3, 750, uiSkin));
        rowArray.add(row4 = new Row(4, 600, uiSkin));
        rowArray.add(row5 = new Row(5, 450, uiSkin));
        rowArray.add(row6 = new Row(6, 300, uiSkin));
        for (Row r : rowArray){
            group.addActor(r);
        }
        setProfile(1);
    }

    public Group getGroup() {
        return group;
    }

    public void setProfile(Integer profile){
        id = profile;
        for (Row r : rowArray) {
            r.setAll(prefs.getBoolean("profile" + profile + "saved"),
                    prefs.getString("profile" + profile + "row" + r.getId() + "text"),
                    prefs.getInteger("profile" + profile + "row" + r.getId() + "MoveMethod"),
                    prefs.getInteger("profile" + profile + "row" + r.getId() + "DelayTime"),
                    prefs.getInteger("profile" + profile + "row" + r.getId() + "Speed"),
                    prefs.getInteger("profile" + profile + "row" + r.getId() + "Font"));
        }
    }


    public void save() {
        prefs.putBoolean("profile" + id + "saved", true);
        for (Row r : rowArray) {
            prefs.putString("profile" + id + "row" + r.getId() + "text", r.getText());
            prefs.putInteger("profile" + id + "row" + r.getId() + "MoveMethod", r.getMoveMethod());
            prefs.putInteger("profile" + id + "row" + r.getId() + "DelayTime", r.getDelayTime());
            prefs.putInteger("profile" + id + "row" + r.getId() + "Speed", r.getSpeed());
            prefs.putInteger("profile" + id + "row" + r.getId() + "Font", r.getFont());

        }
        prefs.flush();
    }

    public Array<Row> getRowArray() {
        return rowArray;
    }
}
