package hu.alerant.formula.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Race implements Comparable<Race>{
    private int year;
    private String name;
    private int round;
    private double multiplier;
    private List<Finisher> finishers;
    private Finisher fastest;

    public Race(int year, String name, int round, double multiplier){
        this.year = year;
        this.name = name;
        this.round = round;
        this.multiplier = multiplier;
        finishers = new ArrayList<>();
        fastest = null;
    }

    public double getMultiplier(){
        return multiplier;
    }

    public int getYear(){
        return year;
    }

    public int getRound() {
        return round;
    }

    public List<Finisher> getResult(){
        List<Finisher> result = new ArrayList<>(finishers);
        result.add(fastest);
        return result;
    }

    public boolean isFastestSet(){
        return fastest != null;
    }

    public void setFastest(Finisher fastest){
        this.fastest = fastest;
        System.out.println("Fastest lap set");
    }

    public int getFinisherCount(){
        return finishers.size();
    }

    public void addFinisher(Finisher f){
        finishers.add(f);
        Collections.sort(finishers);
        System.out.println(f.getName() + " added to race");
    }

    public void addFinishers(List<Finisher> list){
        finishers.addAll(list);
        Collections.sort(finishers);
        System.out.println(list.size() + " finishers added to race");
    }

    public void printResult(){
        Collections.sort(finishers);
        System.out.println(this);
        for (Finisher finisher : finishers) {
            System.out.println(finisher);
        }

        if(fastest != null) System.out.println(fastest);
    }

    @Override
    public String toString(){
        return year + ", " + name + ", " + round + "th";
    }

   @Override
   public boolean equals(Object o){
        if(this == o) return true;

        if(o == null || o.getClass() != this.getClass()) return false;

        Race race = (Race) o;

        return year == race.year
                && round == race.round;
    }

    @Override
    public int compareTo(Race race){
        int yearComparison = Integer.compare(year, race.year);
        if(yearComparison > 0){
            return 1;
        }else if(yearComparison < 0 ){
            return -1;
        }else return Integer.compare(round, race.round);
    }
}
