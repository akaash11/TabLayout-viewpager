package model;

/**
 * Created by akaash on 18/1/18.
 */

public class History {

    private String keyword;
    private int count;

    public History(String keyword, int count) {
        this.keyword = keyword;
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
