package im.mobile.b_b_hobbyist.ui.movie;

import java.io.Serializable;

public class Company implements Serializable {
    private int _id;
    private String companyNm;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }
}
