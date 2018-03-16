package pl.mkrzyzanowski.sigmacontroller.enumValues;

/**
 * Created by Michał on 2017-12-14.
 */

public enum Font {
    FONT_1(1, 0xA0, "5x6 Short"),
    FONT_2(2, 0xA1, "5x11 Short & Wide"),
    FONT_3(3, 0xA2, "7x6 Default"),
    FONT_4(4, 0xA3, "7x11 Wide"),
    FONT_5(5, 0xA4, "7x9"),
    FONT_6(6, 0xA5, "7x17 Extra Wide"),
    FONT_7(7, 0xA6, "Small Font");

    private Integer id;
    private Integer hex;
    private String text;

    Font(Integer id, Integer hex, String text){
        this.id = id;
        this.hex = hex;
        this.text = text;
    }

    public Integer getId(){
        return id;
    }
    public Integer getHex(){
        return hex;
    }
    public String getText(){
        return text;
    }

}
