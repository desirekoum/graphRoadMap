package desire.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface{

    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     *
     */
    Graph graph = new Graph();

    @Override
    public boolean populateTownGraph(File file) throws IOException {
        Scanner sc = new Scanner(file);
        boolean populated = false;
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String lineInfo[] = line.split(";");
            String edgeInfo[] = lineInfo[0].split(",");
            addTown(lineInfo[1]);
            addTown(lineInfo[2]);
            addRoad(lineInfo[1], lineInfo[2], Integer.parseInt(edgeInfo[1]), edgeInfo[0]);
            populated = true;
        }
        return populated;
    }

    /**
     * Adds a road with 2 towns and a road name
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param roadName name of road
     * @return true if the road was added successfully
     */

    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town source = new Town(town1);
        Town destination = new Town(town2);
        boolean added = false;
        if(graph.addEdge(source, destination, weight, roadName)!=null) {
            added = true;
        }
        return added;
    }

    /**
     * Returns the name of the road that both towns are connected through
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return name of road if town 1 and town2 are in the same road, returns null if not
     */
    @Override
    public String getRoad(String town1, String town2) {
        Town source = new Town(town1);
        Town destination = new Town(town2);
        Road road =  graph.getEdge(source, destination);
        return road.getName();
    }

    /**
     * Adds a town to the graph
     * @param v the town's name  (lastname, firstname)
     * @return true if the town was successfully added, false if not
     */
    @Override
    public boolean addTown(String v) {
        Town town = new Town(v);
        return graph.addVertex(town);
    }

    /**
     * Determines if a town is already in the graph
     * @param v the town's name  (lastname, firstname)
     * @return true if the town is in the graph, false if not
     */
    @Override
    public boolean containsTown(String v) {
        Town town = new Town(v);
        return graph.containsVertex(town);
    }

    /**
     * Determines if a road is in the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return true if the road is in the graph, false if not
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Town source = new Town(town1);
        Town destination = new Town(town2);
        return graph.containsEdge(source, destination);
    }

    /**
     * Creates an arraylist of all road titles in sorted order by road name
     * @return an arraylist of all road titles in sorted order by road name
     */
    @Override
    public ArrayList<String> allRoads() {
        List<String> result = new ArrayList<>();
        Set<Road> roads = graph.edgeSet();
        for(Road road : roads) {
            result.add(road.getName());
        }
        Collections.sort(result);
        return (ArrayList<String>)result;
    }

    /**
     * Deletes a road from the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param roadName the road name
     * @return true if the road was successfully deleted, false if not
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        Town source = new Town(town1);
        Town destination = new Town(town2);
        boolean result = false;
        if(graph.removeEdge(source, destination, 0, road)!=null)
            result = true;
        return result ;
    }

    /**
     * Deletes a town from the graph
     * @param v name of town (lastname, firstname)
     * @return true if the town was successfully deleted, false if not
     */

    @Override
    public boolean deleteTown(String v) {
        Town town = new Town(v);
        return graph.removeVertex(town);
    }

    /**
     * Creates an arraylist of all towns in alphabetical order (last name, first name)
     * @return an arraylist of all towns in alphabetical order (last name, first name)
     */
    @Override
    public ArrayList<String> allTowns() {
        Set<Town> towns = graph.vertexSet();
        List<String> result = new ArrayList<>();
        for(Town t:towns) {
            result.add(t.getName());
        }
        Collections.sort(result);
        return (ArrayList<String>) result;
    }

    /**
     * Returns the shortest path from town 1 to town 2
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return an Arraylist of roads connecting the two towns together, null if the
     * towns have no path to connect them.
     * They will be in the format: startTown "via" road "to" endTown weight "miles"
     * The last string in the ArrayList will be the total miles of the path in the
     * following format: Total miles: #totalMiles
     * As an example: if finding path from Town_1 to Town_10, the ArrayList<String>
     * would be in the following format(this is a hypothetical solution):
     * Town_1 via Road_2 to Town_3 4 miles (first string in ArrayList)
     * Town_3 via Road_5 to Town_8 2 miles (second string in ArrayList)
     * Town_8 via Road_9 to Town_10 2 miles (third string in ArrayList)
     * Total miles: 8 miles
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town source = new Town(town1);
        Town destination = new Town(town2);
        graph.dijkstraShortestPath(source);
        ArrayList<String> sPath = graph.shortestPath(source, destination);
        System.out.println("Get Path:::"+sPath);
        ArrayList<String> list = new ArrayList<>();
        for(String s: sPath){
            String element = s;
            list.add(element);
        }
        return list;
    }

    public Town getTown(String town1) {
        Town source = new Town(town1);
        return source;

    }
}
