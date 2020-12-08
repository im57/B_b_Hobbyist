package im.mobile.b_b_hobbyist.ui.movie;

import java.io.Serializable;

public class Actor implements Serializable {
    private int _id;
    private String peopleNm;
    private String cast;

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

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
