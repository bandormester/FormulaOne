package hu.alerant.formula.commands;

import hu.alerant.formula.data.Query;

public class QueryCommand implements Command<Query>{
    private int year;
    private int round;
    private String pointRule;
    private boolean isFullYear;

    public QueryCommand(){
        year = 0;
        round = 0;
        pointRule = "PRESENT";
        isFullYear = true;
    }

    public boolean setPointRule(String[] arguments){
        if(arguments.length == 2)
        switch(arguments[1]){
            case "PRESENT":
            case "NEW":
            case "MODERN":
            case "CLASSIC":
                System.out.println("Point rule set");
                pointRule = arguments[1];
                return true;
            default:
                System.out.println("Possible point rules are CLASSIC , MODERN , NEW , PRESENT");
                return false;
        }
        System.out.println("Point command have one argument PointRule(String)");
        return false;
    }

    public void printBadInput() {
        System.out.println("The command arguments are Year(int) or Year(int) ; Round(int)");
    }

    public Query execute() {
        if(isFullYear){
            return new Query(year, pointRule);
        } else {
            return new Query(year, round, pointRule);
        }
    }

    public boolean setArguments(String[] arguments) {
        if(arguments.length > 3){
            printBadInput();
            return false;
        }
        if(arguments.length >= 2){
            try{
                this.year = Integer.parseInt(arguments[1]);
            }catch (NumberFormatException e){
                printBadInput();
                return false;
            }

            if(arguments.length == 3){
                try{
                    this.round = Integer.parseInt(arguments[2]);
                    isFullYear = false;
                }catch (NumberFormatException e){
                    printBadInput();
                    return false;
                }
            }
        } else{
            printBadInput();
            return false;
        }
        return true;
    }
}