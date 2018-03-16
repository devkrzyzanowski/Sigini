package pl.mkrzyzanowski.sigmacontroller.enumValues;

/**
 * Created by Michał on 2017-12-05.
 */

public enum DelayTime {
    DELAY_0(0),
    DELAY_1(1),
    DELAY_2(2),
    DELAY_3(3),
    DELAY_4(4),
    DELAY_5(5),
    DELAY_6(6),
    DELAY_7(7),
    DELAY_8(8);

    private Integer i;

    DelayTime(int i) {
        this.i = i;
    }

    public Integer getTime(){
        return i;
    }
}
