package desire.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * Represents an town as a node of a graph. The Town class holds the name of the town and a list of adjacent towns,
 * and other fields as desired, and the traditional methods (constructors, getters/setters, toString, etc.).
 * It will implement the Comparable interface These are the minimum methods that are needed. Please feel free to add
 * as many methods as you need.
 */

public class Town implements Comparable<Town> {
    private String name;
    //private List<Town> adjacentTowns;

    private List<Town> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    Map<Town, Integer> adjacentTowns = new HashMap<>();

    public Town(String name) {
        this.name = name;
        //adjacentTowns = new ArrayList<>();
    }

    public Town(Town templateTown) {
        this.name = templateTown.name;
        //this.adjacentTowns = templateTown.adjacentTowns;
    }

    public String getName() {
        return this.name;
    }

    public Town getTown() {
        return this;
    }

    @Override
    public int compareTo(Town o) {
        return name.compareToIgnoreCase(o.name);
    }

    public String toString(){
        return name;

    }

    @Override
    public int hashCode() {
        return name.hashCode();

    }
    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass()!=obj.getClass()) return false;
        Town town  = (Town) obj;
        return this.name.equals(town.name);

    }

}