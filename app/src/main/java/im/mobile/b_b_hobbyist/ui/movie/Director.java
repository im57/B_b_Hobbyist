package im.mobile.b_b_hobbyist.ui.movie;

import java.io.Serializable;

public class Director implements Serializable {
    private int _id;
    private String peopleNm;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPeopleNm() {
        return peopleNm;
    }

    public void setPeopleNm(String peopleNm) {
        this.peopleNm = peopleNm;
    }
}
