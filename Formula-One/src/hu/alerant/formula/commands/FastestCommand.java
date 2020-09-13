package hu.alerant.formula.commands;

import hu.alerant.formula.model.Finisher;

public class FastestCommand implements Command<Finisher> {
    private String name;
    private String team;

    public FastestCommand(){
        name = "";
        team = "";
    }

    public void printBadInput() {
        System.out.println("The command arguments are Name(String) ; Team(String)");
    }

    public Finisher execute() {
        return new Finisher(name, team);
    }

    public boolean setArguments(String[] arguments) {

        if(arguments.length != 3){
            printBadInput();
            return false;
        }
        this.name = arguments[1];
        this.team = arguments[2];
        return true;
    }
}
