package hu.alerant.formula;

import hu.alerant.formula.commands.*;
import hu.alerant.formula.data.FormulaDatabase;
import hu.alerant.formula.model.Race;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FormulaApp {
    private FormulaDatabase database;
    private List<String> nextCommands;
    private boolean running;
    private QueryCommand queryCommand;
    private Race newRace;


    public FormulaApp(){
        queryCommand = new QueryCommand();
        database = new FormulaDatabase();
        nextCommands = new ArrayList<>();
        newRace = null;
        running = true;
        nextCommands.add("RACE");
        nextCommands.add("QUERY");
    }

    public void chooseCommand(String[] arguments){
        if(arguments[0].equals("EXIT")){
            running = false;
            return;
        }
        if(nextCommands.contains(arguments[0])) {
            switch (arguments[0]) {
                case "RACE":
                    handleRaceCommand(arguments);
                    break;
                case "RESULT":
                    handleResultCommand(arguments);
                    break;
                case "FASTEST":
                    handleFastestCommand(arguments);
                    break;
                case "FINISH":
                    handleFinishCommand();
                    break;
                case "QUERY":
                    handleQueryCommand(arguments);
                    break;
                case "POINT":
                    handlePointCommand(arguments);
                    break;
            }
        } else{
            System.out.println("Possible commands are " + nextCommands);
        }
    }

    public void run() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("input-hf.txt"));
            String line = br.readLine();
            while (line != null){
                String[] arguments = line.split(";");
                chooseCommand(arguments);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        while(running){
            String[] arguments = readInput();
            if(arguments==null) break;
            chooseCommand(arguments);
        }
    }

    private void handleRaceCommand(String[] arguments){
        RaceCommand raceCommand = new RaceCommand();
        if(raceCommand.setArguments(arguments)){
            newRace = raceCommand.execute();
            System.out.println("Race prepared");
            nextCommands.clear();
            nextCommands.add("RESULT");
            nextCommands.add("FINISH");
            nextCommands.add("FASTEST");
        }
    }

    private void handleQueryCommand(String[] arguments) {
        queryCommand = new QueryCommand();
        if(queryCommand.setArguments(arguments)){
            System.out.println("Query command arguments set");
            nextCommands.clear();
            nextCommands.add("POINT");
            nextCommands.add("QUERY");
        }
    }

    private void handlePointCommand(String[] arguments) {
        if(queryCommand.setPointRule(arguments)) {
            database.runQuery(queryCommand.execute());
            nextCommands.clear();
            nextCommands.add("QUERY");
            nextCommands.add("RACE");
        }
    }

    private void handleFinishCommand() {
        if(newRace.getFinisherCount() >= 10 && newRace.isFastestSet()){
            database.addRace(newRace);
            nextCommands.clear();
            nextCommands.add("RACE");
            nextCommands.add("QUERY");
        }
        else {
            System.out.println("Not enough finishers, or fastest not set at this race");
        }
    }

    private void handleFastestCommand(String[] arguments) {
        FastestCommand fastestCommand = new FastestCommand();
        if(fastestCommand.setArguments(arguments)){
            newRace.setFastest(fastestCommand.execute());
        }
    }

    private void handleResultCommand(String[] arguments) {
        ResultCommand resultCommand = new ResultCommand();
        if(resultCommand.setArguments(arguments)){
            newRace.addFinisher(resultCommand.execute());
        }
    }

    private String[] readInput(){
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println("Bad input!");
            return null;
        }
        return line.split(";");
    }
}
