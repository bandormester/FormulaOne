package hu.alerant.formula.data.scoreboard;

import hu.alerant.formula.model.Finisher;
import hu.alerant.formula.model.Race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard {
    private List<Score> scores;
    private String pointRule;
    private int[] points;

    public ScoreBoard(){
        scores = new ArrayList<>();
    }

    public void setPointRule(String rule){
        switch(rule){
            case "MODERN":
                points = new int[]{10, 8, 6, 5, 4, 3, 2, 1};
                break;
            case "CLASSIC":
                points = new int[]{10, 6, 4, 3, 2, 1};
                break;
            case "PRESENT":
            case "NEW":
                points = new int[]{25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
                break;
        }
        pointRule = rule;
    }

    public int getSize(){
        return scores.size();
    }

    public void addResult(Race race){
        List<Finisher> result = race.getResult();
        double multiplier = race.getMultiplier();

        int i = 0;
        while(i < result.size() - 1){
            Finisher finisher = result.get(i);
            double plusPoints = 0.0;

            if(i < points.length)
                plusPoints = points[i] * multiplier;
            if(pointRule.equals("PRESENT")) if(finisher.equals(result.get(result.size()-1))) plusPoints += multiplier;

            addScore(new Score(finisher, plusPoints));

            i++;
        }
    }

    private void addScore(Score addedScore){
        int j = 0;

        while(j < scores.size()){
            if(scores.get(j).equals(addedScore)){
                scores.get(j).addPoints(addedScore.getPoints());
                break;
            }
            j++;
        }
        if(j == scores.size()) scores.add(addedScore);
    }

    public void print(){
        Collections.sort(scores);
        for(int i = 0; i < scores.size(); i++){
            System.out.println(" " + (i + 1) + ". " + scores.get(i));
        }
    }
}
