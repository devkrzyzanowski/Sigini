package pl.mkrzyzanowski.sigmacontroller.enumValues;

/**
 * Created by Michał on 2017-12-05.
 */

public enum Profiles {
    PROFILE_1(1, "Profil 1"),
    PROFILE_2(2, "Profil 2"),
    PROFILE_3(3, "Profil 3"),
    PROFILE_4(4, "Profil 4"),
    PROFILE_5(5, "Profil 5");

    private String s;
    private Integer id;

    Profiles(Integer id, String s) {
        this.id = id;
        this.s = s;
    }

    public String getString(){
        return s;
    }

    public Integer getId() {
        return id;
    }
}
