package desire.graph;

public class Town implements Comparable<Town> {

    private String name;

    public Town(String name) {
        this.name = name;
    }

    public Town(Town town) {
        this.name = town.name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Town town = (Town) o;

        return name.equals(town.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Town o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public String toString() {
        return "Town{" +
                "name='" + name + '\'' +
                '}';
    }
}
