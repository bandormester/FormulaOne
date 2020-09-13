package hu.alerant.formula.data;

public class Query {
    private int year;
    private int round;
    private String pointRule;
    private boolean isFullYear;

    public Query(int year, String pointRule){
        this.year = year;
        this.pointRule = pointRule;
        isFullYear = true;
    }

    public Query(int year, int round, String pointRule){
        this.year = year;
        this.pointRule = pointRule;
        this.round = round;
        isFullYear = false;
    }

    public String getPointRule(){ return pointRule; }

    public boolean isFullYear() {
        return isFullYear;
    }

    public int getRound() {
        return round;
    }

    public int getYear() {
        return year;
    }
}
