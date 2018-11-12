package desire.graph;

import java.util.ArrayList;

public class TownGraphManager implements TownGraphManagerInterface {
    /**
     * Adds a road with 2 towns and a road name
     *
     * @param town1    name of town 1 (lastname, firstname)
     * @param town2    name of town 2 (lastname, firstname)
     * @param weight
     * @param roadName name of road
     * @return true if the road was added successfully
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        return false;
    }

    /**
     * Returns the name of the road that both towns are connected through
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return name of road if town 1 and town2 are in the same road, returns null if not
     */
    @Override
    public String getRoad(String town1, String town2) {
        return null;
    }

    /**
     * Adds a town to the graph
     *
     * @param v the town's name  (lastname, firstname)
     * @return true if the town was successfully added, false if not
     */
    @Override
    public boolean addTown(String v) {
        return false;
    }

    /**
     * Determines if a town is already in the graph
     *
     * @param v the town's name  (lastname, firstname)
     * @return true if the town is in the graph, false if not
     */
    @Override
    public boolean containsTown(String v) {
        return false;
    }

    /**
     * Determines if a road is in the graph
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return true if the road is in the graph, false if not
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return false;
    }

    /**
     * Creates an arraylist of all road titles in sorted order by road name
     *
     * @return an arraylist of all road titles in sorted order by road name
     */
    @Override
    public ArrayList<String> allRoads() {
        return null;
    }

    /**
     * Deletes a road from the graph
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param road
     * @return true if the road was successfully deleted, false if not
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        return false;
    }

    /**
     * Deletes a town from the graph
     *
     * @param v name of town (lastname, firstname)
     * @return true if the town was successfully deleted, false if not
     */
    @Override
    public boolean deleteTown(String v) {
        return false;
    }

    /**
     * Creates an arraylist of all towns in alphabetical order (last name, first name)
     *
     * @return an arraylist of all towns in alphabetical order (last name, first name)
     */
    @Override
    public ArrayList<String> allTowns() {
        return null;
    }

    /**
     * Returns the shortest path from town 1 to town 2
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return an Arraylist of roads connecting the two towns together, null if the
     * towns have no path to connect them.
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        return null;
    }
}
