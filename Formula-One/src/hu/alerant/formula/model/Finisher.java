package hu.alerant.formula.model;

public class Finisher implements Comparable<Finisher> {
    private Integer placement;
    private String name;
    private String team;

    public Finisher(int placement, String name, String team){
        this.placement = placement;
        this.name = name;
        this.team = team;
    }

    public Finisher(String name, String team){
        placement = null;
        this.name = name;
        this.team = team;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return placement == null ? name + " " + team : placement.toString() + ". " + name + " " + team;
    }

    @Override
    public int compareTo(Finisher o) {
        return Integer.compare(placement, o.placement);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;

        if(o == null || o.getClass() != this.getClass()) return false;

        Finisher finisher = (Finisher) o;

        return name.equals(finisher.name);
    }
}
