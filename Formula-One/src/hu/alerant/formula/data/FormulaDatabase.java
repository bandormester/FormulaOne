package hu.alerant.formula.data;

import hu.alerant.formula.data.scoreboard.ScoreBoard;
import hu.alerant.formula.model.Race;

import java.util.ArrayList;
import java.util.List;

public class FormulaDatabase {
    private List<Race> races;
    ScoreBoard scoreBoard;

    public FormulaDatabase(){
        races = new ArrayList<>();
        scoreBoard = new ScoreBoard();
    }

    public void addRace(Race race){
        races.add(race);
        System.out.println(race.getYear() + " " + race.getRound() + "th race added to database");
    }

    public void printResult(int year){
        for(Race race : races){
            if(race.getYear() == year) scoreBoard.addResult(race);
        }
        if(scoreBoard.getSize() == 0){
            System.out.println(" No results from year " + year);
        }
        else{
            System.out.println(" Results of year " + year);
            scoreBoard.print();
        }
    }

    public void runQuery(Query query){
        scoreBoard = new ScoreBoard();
        scoreBoard.setPointRule(query.getPointRule());

       if(query.isFullYear()){
           printResult(query.getYear());
       } else {
           printResult(query.getYear(), query.getRound());
       }
    }

    public void printResult(int year, int round){
        for(Race race : races){
            if(race.getYear() == year && race.getRound() <= round) scoreBoard.addResult(race);
        }
        if(scoreBoard.getSize() == 0)
            System.out.println(" No results from year " + year + " until round " + round);
        else{
            System.out.println(" Results of year " + year + " at round " + round);
            scoreBoard.print();
        }
    }
}
