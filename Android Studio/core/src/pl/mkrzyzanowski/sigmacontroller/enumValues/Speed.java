package pl.mkrzyzanowski.sigmacontroller.enumValues;

/**
 * Created by Michał on 2017-12-14.
 */

public enum Speed {
    SPEED_1(1, 0xC0, "SZYBKO 4"),
    SPEED_2(2, 0xC1, "SZYBKO 3"),
    SPEED_3(3, 0xC2, "SZYBKO 2"),
    SPEED_4(4, 0xC3, "SZYBKO 1"),
    SPEED_5(5, 0xC4, "WOLNO 1"),
    SPEED_6(6, 0xC5, "WOLNO 2"),
    SPEED_7(7, 0xC6, "WOLNO 3"),
    SPEED_8(8, 0xC7, "WOLNO 4");

    private Integer id;
    private Integer hex;
    private String text;

    Speed(Integer id, Integer hex, String text){
        this.id = id;
        this.hex = hex;
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public Integer getHex(){
        return hex;
    }

}
