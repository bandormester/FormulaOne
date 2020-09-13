package hu.alerant.formula.commands;

import hu.alerant.formula.model.Race;

public class RaceCommand implements Command<Race> {
    private int year;
    private String name;
    private int round;
    private double multiplier;

    public RaceCommand(){
        year = 0;
        name = "";
        round = 0;
        multiplier = 1.0;
    }

    public void printBadInput() {
        System.out.println("The command arguments are Year(int) ; Name(String) ; Round(int) ; Multiplier(double)");
    }

    public boolean setArguments(String[] arguments){
        if(arguments.length != 5){
            printBadInput();
            return false;
        }
        try{
            this.year = Integer.parseInt(arguments[1]);
            this.name = arguments[2];
            this.round = Integer.parseInt(arguments[3]);
            this.multiplier = Double.parseDouble(arguments[4]);
        }
        catch (NumberFormatException e){
            printBadInput();
            return false;
        }

        if(multiplier != 0 && multiplier != 0.5 && multiplier != 1 && multiplier != 2){
            System.out.println("Multiplier should be 0, 0.5, 1, 2");
            return false;
        }
        return true;
    }

    public Race execute() {
        return new Race(year, name, round, multiplier);
    }
}
