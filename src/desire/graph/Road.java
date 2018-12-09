package desire.graph;

/*
 * The class Road that can represent the edges of a Graph of Towns. The class must implement Comparable.
 *  The class stores references to the two vertices(Town endpoints), the distance between vertices, and
 *   a name, and the traditional methods (constructors, getters/setters, toString, etc.), and a compareTo,
 *   which compares two Road objects. Since this is a undirected graph, an edge from A to B is equal to an
 *    edge from B to A.
 */
public class Road implements Comparable<Road> {

    private Town source;
    private Town destination;
    private String roadName;
    private int distance;
    /*
     *  contructors
     */
    public Road(Town town, Town town2, int distance, String name) {
        this.source = town;
        this.destination = town2;
        this.distance = distance;
        this.roadName = name;
    }


    public Road(Town source, Town destination, String name) {
        this.source = source;
        this.destination = destination;
        this.distance = 0;
        this.roadName = name;
    }

    public Road(Town source, Town destination) {
        this.source = source;
        this.destination = destination;
        this.distance = 0;
    }

    public Road() {
    }


    public String getName() {

        return roadName;
    }

    public boolean contains(Town town) {
        if(this.source.equals(town) || this.destination.equals(town))
            return true;
        return false;
    }

    public Town getDestination() {
        return destination;
    }

    public Town getSource() {
        return source;
    }

    public int compareTo(Road o) {
        return roadName.compareToIgnoreCase(o.getName());
    }

    public int getWeight() {
        return distance;
    }

    /*
     * Returns true if each of the ends of the road r is
     * the same as the ends of this road. Remember
     * that a road that goes from point A to point B is the same as a
     * road that goes from point B to point A.
     * @return boolean
     */
    @Override
    public boolean equals(Object r) {
        if (this == r) return true;
        if (r == null) return false;
        if (getClass() != r.getClass()) return false;
        Road road = (Road) r;
        return destination.equals(road.destination) && source.equals(road.source);
    }

    public String toString(){
        String s = this.source+" via "+this.roadName +" to "+this.destination+" "+this.getWeight();
        return s;
    }
}