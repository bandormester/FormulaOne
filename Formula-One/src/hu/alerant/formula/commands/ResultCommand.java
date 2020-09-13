package hu.alerant.formula.commands;

import hu.alerant.formula.model.Finisher;

public class ResultCommand implements Command<Finisher> {
    private int placement;
    private String name;
    private String team;

    public ResultCommand(){
        placement = 0;
        name = "";
        team = "";
    }

    public void printBadInput() {
        System.out.println("The command arguments are Placement(int) ; Name(String) ; Team(String)");
    }

    public Finisher execute() { return new Finisher(placement, name, team); }

    public boolean setArguments(String[] arguments) {
        if(arguments.length != 4){
            printBadInput();
            return false;
        }
        try{
            this.placement = Integer.parseInt(arguments[1]);
            this.name = arguments[2];
            this.team = arguments[3];
            return true;
        }
        catch (NumberFormatException e){
            printBadInput();
            return false;
        }
    }
}
