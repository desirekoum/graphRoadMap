package desire.graph;

public class Road implements Comparable<Road> {

    private String name;
    private Town source;
    private Town destination;
    private int distance;

    public Road(Town source, Town destination, int distance, String name){
        this.source = source;
        this.destination = destination;
        this.name = name;
        this.distance = distance;
    }

    public Road(Town source, Town destination, String name){
        this.source = source;
        this.destination = destination;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getSource() {
        return source;
    }

    public Town getDestination() {
        return destination;
    }

    public int getWeight() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Road road = (Road) o;

        return destination.equals(road.destination) && source.equals(road.source);
    }

    @Override
    public int hashCode() {
        return destination.hashCode();
    }

    private boolean contains(Town town){
        return this.getSource().equals(town.getName()) ||
                this.getDestination().equals(town.getName());
    }

    public int compareTo(Road road){
        return name.compareToIgnoreCase(road.getName());
    }

    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", source=" + source +
                ", destination=" + destination +
                ", distance=" + distance + " miles "+
                '}';
    }
}