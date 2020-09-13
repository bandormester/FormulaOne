package hu.alerant.formula.commands;

public interface Command<T>{
    void printBadInput();
    T execute();
    boolean setArguments(String[] arguments);
}



