package pl.mkrzyzanowski.sigmacontroller.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import pl.mkrzyzanowski.sigmacontroller.enumValues.*;

/**
 * Created by Michał on 2017-12-03.
 */

public class Row extends Group {
    Label number;

    private String text;
    private Integer moveMethod =0;
    private Integer delayTime =0;

    SelectBox<String> optionSelectBox;

    TextField textField;
    SelectBox<String> moveMethodsSelectBox;
    //SelectBox<Integer> waitTimeSelectBox; todo add delays
    SelectBox<String> speedSelectBox;
    SelectBox<String> fontSelectBox;
    ImageButton trash;

    Array<String> optionsArray;
    Array<String> moveMethodsArray;
    Array<Integer> waitTimeArray;
    Array<String> speedArray;
    Array<String> fontArray;

    private boolean isChanged = true;
    private Integer id;
    private int speed;
    private int font;


    public Row(Integer id, float y, Skin skin){
        this.id = id;
        setPosition(0, y);
        setSize(720, 100);

        number = new Label(" " + String.valueOf(id), skin);
        number.setPosition(5, 0);
        number.setSize(15, 100);
        //addActor(number);

        optionSelectBox = new SelectBox<String>(skin);
        optionSelectBox.setPosition(15, 0);
        optionSelectBox.setSize(235, 100);

        optionsArray = new Array<String>();
        optionsArray.add("TEKST");
        optionsArray.add("PRZEJSCIE");
        //optionsArray.add("czas");
        optionsArray.add("TEMPO");
        optionsArray.add("CZCIONKA");
        optionSelectBox.setItems(optionsArray);

        textField = new TextField("", skin);
        textField.setPosition(260, 0);
        textField.setSize(335, 100);
        textField.setMaxLength(15);

        trash = new ImageButton(skin, "trash");
        trash.setPosition(605, 0);
        trash.setSize(100, 100);


        moveMethodsSelectBox = new SelectBox<String>(skin);
        moveMethodsSelectBox.setPosition(260, 0);
        moveMethodsSelectBox.setSize(445, 100);

        speedSelectBox = new SelectBox<String>(skin);
        speedSelectBox.setPosition(260, 0);
        speedSelectBox.setSize(445, 100);

        speedArray = new Array<String>();
        for (Speed s : Speed.values()){
            speedArray.add(s.getText());
        }
        speedSelectBox.setItems(speedArray);

        fontArray = new Array<String>();
        for (Font f : Font.values()){
            fontArray.add(f.getText());
        }
        fontSelectBox = new SelectBox<String>(skin);
        fontSelectBox.setPosition(260, 0);
        fontSelectBox.setSize(445, 100);
        fontSelectBox.setItems(fontArray);
        fontSelectBox.setSelectedIndex(2);

       // waitTimeSelectBox = new SelectBox<Integer>(skin);
       // waitTimeSelectBox.setPosition(280, 0);
        //waitTimeSelectBox.setSize(720 - 285, 100);


        moveMethodsArray = new Array<String>();
        for (MoveMethod moveMethod : MoveMethod.values()){
            moveMethodsArray.add(moveMethod.getString());
        }
        moveMethodsSelectBox.setItems(moveMethodsArray);
        moveMethodsSelectBox.setSelectedIndex(1);

        waitTimeArray = new Array<Integer>();
        for (int i = 1; i <= 8; i++){
            waitTimeArray.add(i);
        }
        //waitTimeSelectBox.setItems(waitTimeArray);


        addActor(optionSelectBox);

        optionSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isChanged = true;
            }
        });

        moveMethodsSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                moveMethod = moveMethodsSelectBox.getSelectedIndex();
            }
        });
        trash.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                textField.setText("");
            }
        });
//        waitTimeSelectBox.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                delayTime = waitTimeSelectBox.getSelectedIndex();
//            }
//        });
        speedSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                speed = speedSelectBox.getSelectedIndex();
            }
        });
        fontSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                font = fontSelectBox.getSelectedIndex();
            }
        });

    }

    public void act(float delta) {
        text = textField.getText();


        if (isChanged) {
            removeActor(textField);
            removeActor(trash);
            removeActor(moveMethodsSelectBox);
            removeActor(speedSelectBox);
            removeActor(fontSelectBox);

            if (optionSelectBox.getSelected().equals("TEKST")) {
                addActor(textField);
                addActor(trash);
                isChanged = false;
            } else if (optionSelectBox.getSelected().equals("PRZEJSCIE")) {
                addActor(moveMethodsSelectBox);
                removeActor(trash);
                isChanged = false;
            } else if (optionSelectBox.getSelected().equals("TEMPO")){
                addActor(speedSelectBox);
                isChanged = false;
            } else if (optionSelectBox.getSelected().equals("CZCIONKA")){
                addActor(fontSelectBox);
                isChanged = false;
            }
        }
    }


    void setAll(Boolean saved, String text, Integer moveMethod, Integer delayTime, Integer speed, Integer font) {
        optionSelectBox.setSelectedIndex(0);
        if (saved) {
            textField.setText(text);
            moveMethodsSelectBox.setSelectedIndex(moveMethod);
            speedSelectBox.setSelectedIndex(speed);
            fontSelectBox.setSelectedIndex(font);
        } else {
            textField.setText("");
            moveMethodsSelectBox.setSelectedIndex(1);
            speedSelectBox.setSelectedIndex(0);
            fontSelectBox.setSelectedIndex(2);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getMoveMethod() {
        return moveMethod;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public TextField getTextField() {
        return textField;
    }

    public int getSpeed() {
        return speed;
    }

    public int getFont() {
        return font;
    }
}
