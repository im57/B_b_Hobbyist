package im.mobile.b_b_hobbyist.ui.movie;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {
    private int _id;
    private String movieCd;     //영화코드
    private String movieNm;     //영화제목
    private String openDt;      //개봉일
    private String nationAlt;   //제작국가
    private String genreAlt;    //장르
    private ArrayList<Director> directors;  //감독
    private ArrayList<Company> companys;  //제작사
    private ArrayList<Actor> actors;      //배우

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMovieCd() {
        return movieCd;
    }

    public void setMovieCd(String movieCd) {
        this.movieCd = movieCd;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getOpenDt() {
        return openDt;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public String getNationAlt() {
        return nationAlt;
    }

    public void setNationAlt(String nationAlt) {
        this.nationAlt = nationAlt;
    }

    public String getGenreAlt() {
        return genreAlt;
    }

    public void setGenreAlt(String genreAlt) {
        this.genreAlt = genreAlt;
    }

    public ArrayList<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<Director> directors) {
        this.directors = directors;
    }

    public ArrayList<Company> getCompanys() {
        return companys;
    }

    public void setCompanys(ArrayList<Company> companys) {
        this.companys = companys;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }
}
