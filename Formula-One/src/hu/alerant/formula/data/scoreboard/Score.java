package hu.alerant.formula.data.scoreboard;

import hu.alerant.formula.model.Finisher;

public class Score implements Comparable<Score> {
    private Finisher finisher;
    private double points;

    public Score(Finisher finisher, double points){
        this.finisher = finisher;
        this.points = points;
    }

    public Finisher getFinisher() {
        return finisher;
    }

    public double getPoints(){return points;}

    public void addPoints(double points){
        this.points += points;
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;

        if(o == null || o.getClass() != this.getClass()) return false;

        Score score = (Score) o;

        return finisher.equals(score.finisher);
    }

    @Override
    public String toString(){
        return finisher.getName() + " " + points;
    }

    @Override
    public int compareTo(Score score) {
        return Double.compare(score.points, points);
    }
}
