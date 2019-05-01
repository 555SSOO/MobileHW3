package rs.edu.raf.movies.repository.web.model;

import com.google.gson.annotations.SerializedName;


public class Movie {

    @SerializedName("id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("rt_score")
    private String mScore;
    @SerializedName("release_date")
    private String mYear;
    @SerializedName("director")
    private String mDirector;


    public Movie(String title) {
        this(title, "11", "Miyazaki", "1999");
    }

    public Movie(String title, String score, String director, String year) {
        mTitle = title;
        mScore = score;
        mDirector = director;
        mYear = year;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmScore() {
        return mScore;
    }

    public void setmScore(String mScore) {
        this.mScore = mScore;
    }

    public String getmDirector() {
        return mDirector;
    }

    public void setmDirector(String mDirector) {
        this.mDirector = mDirector;
    }

    public String getmYear() {
        return mYear;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }

}
