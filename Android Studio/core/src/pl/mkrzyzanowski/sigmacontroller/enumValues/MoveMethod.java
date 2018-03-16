package pl.mkrzyzanowski.sigmacontroller.enumValues;

/**
 * Created by Michał on 2017-12-05.
 */

public enum MoveMethod{
    CYCLIC(1, "Po kolei", 0x01),
    IMMEDIATE(2, "Natychmiast", 0x02),
    OPEN_FROM_RIGHT(3, "Od prawej", 0x03),
    OPEN_FROM_LEFT(4, "Od lewej", 0x04),
    OPEN_FROM_CENTER(5, "Od srodka", 0x05),
    OPEN_TO_CENTER(6, "OPEN_TO_CENTER", 0x05),
    COVER_FROM_CENTER(7, "COVER_FROM_CENTER", 0x06),
    COVER_FROM_RIGHT(8, "COVER_FROM_RIGHT", 0x07),
    COVER_FROM_LEFT(9, "COVER_FROM_LEFT", 0x08),
    COVER_TO_CENTER(10, "COVER_TO_CENTER", 0x09),
    SCROLL_UP(11, "SCROLL_UP", 0x0A),
    SCROLL_DOWN(12, "SCROLL_DOWN", 0x0B),
    INTERLACE_TO_CENTER(13, "INTERLACE_TO_CENTER", 0x0C),
    INTERLACE_COVER(14, "INTERLACE_COVER", 0x0D),
    COVER_UP(15, "COVER_UP", 0x0E),
    COVER_DOWN(16, "COVER_DOWN", 0x0F),
    SCAN_LINE(17, "SCAN_LINE", 0x10),
    EXPLODE(18, "EXPLODE", 0x11),
    PAC_MAN(19, "PAC_MAN", 0x12),
    FALL_STACK(20, "FALL_STACK", 0x13),
    SHOOT(21, "SHOOT", 0x14),
    FLASH(22, "FLASH", 0x15),
    RANDOM(23, "RANDOM", 0x16),
    AUTO(24, "AUTO", 0x17);


    private String s;
    private Integer id;
    private Integer hex;

    MoveMethod(Integer id, String s, Integer hex) {
        this.id = id;
        this.s = s;
        this.hex = hex;
    }

    public String getString() {
        return s;
    }

    public int getId() {
        return id;
    }

    public int getHex(){
        return hex;
    }
}

