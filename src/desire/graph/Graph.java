package desire.graph;

import java.util.*;

public class Graph implements GraphInterface<Town, Road> {

    private Map<Town, Set<Road>> graph;//T1 -> {road1, road2....}
    private  List<Town> nodes = new ArrayList<>();
    private  List<Road> edges  = new ArrayList<>();
    private Set<Town> visitedTowns = new HashSet<Town>();
    private Set<Town> unvisitedTowns= new HashSet<Town>();
    private Map<Town, Town> predecessors = new HashMap<Town, Town>();
    private Map<Town, Integer> distance = new HashMap<Town, Integer>();

    //Constructor
    public Graph() {
        graph = new HashMap<Town, Set<Road>>();
    }

    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        Road road = null;
        if(sourceVertex==null || destinationVertex==null)
            return null;
        if(!graph.containsKey(sourceVertex) || !graph.containsKey(destinationVertex)) {
            return null;
        }
        Set<Road> roads = graph.get(sourceVertex);
        for(Road r : roads) {
            if(r.getDestination().equals(destinationVertex)) {
                road = r;
                break;
            }
        }
        return road;

    }

    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge.
     *
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     * */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
            throws NullPointerException, IllegalArgumentException{

        if(!graph.containsKey(sourceVertex) || !graph.containsKey(destinationVertex)) {
            throw new IllegalArgumentException("Exception::: No such vertex in the graph");
        }
        if(sourceVertex==null || destinationVertex == null) {
            throw new NullPointerException("Exception::: Vertices cannot be null or Empty");
        }

        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        Set<Road> edges = graph.get(sourceVertex);
        edges.add(road);
        return road;
    }

    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    @Override
    public boolean addVertex(Town v) {
        boolean added = false;
        try {
            if(v==null) {
                throw new NullPointerException("Exception caught::: the town cannot be null");
            }
            else {
                graph.put(v, new HashSet<Road>());
                added = true;
            }

        }catch(NullPointerException e) {
            e.getMessage();
        }
        return added;
    }

    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {//ca marche
        boolean contains = false;

        if(sourceVertex==null || destinationVertex==null) {
            contains = false;
        }

        if(!this.containsVertex(sourceVertex) || !this.containsVertex(destinationVertex)) {
            contains = false;
        }

        if(graph.containsKey(sourceVertex)) {
            Set<Road> roads = graph.get(sourceVertex);
            for(Road r:roads) {
                if(r.getDestination().equals(destinationVertex)) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }

    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
    @Override
    public boolean containsVertex(Town v) {
        boolean contains = false;
        for(Town town: graph.keySet()) {
            if(town.equals(v)) {
                contains = true;
            }
        }
        return contains;
    }


    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
    @Override
    public Set<Road> edgeSet() {
        Set<Road> result = new HashSet<>();
        for(Town town: graph.keySet()) {
            Set<Road> road = graph.get(town);
            for(Road r: road) {
                result.add(r);
            }
        }
        return result;
    }

    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {
        Set<Road> roads = null;
        try
        {
            if(vertex ==null)
                throw new NullPointerException("Exception::: the town cannot be null");
            if(!graph.containsKey(vertex))
                throw new IllegalArgumentException("Exception::: No such Town in the graph");

            roads = graph.get(vertex);

        }catch(NullPointerException  | IllegalArgumentException e) {
            e.getMessage();
        }
        return roads;
    }

    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph.
     *
     * If weight >- 1 it must be checked
     * If description != null, it must be checked
     *
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road currentRoad = new Road();
        if((weight > -1)  && description!=null && this.containsEdge(sourceVertex, destinationVertex) ) {
            Set<Road> roads = graph.get(sourceVertex);
            Iterator<Road> it = roads.iterator();
            while(it.hasNext()){
                currentRoad = it.next();
                if(currentRoad.getDestination().equals(destinationVertex)) {
                    break;
                }
            }
            roads.remove(currentRoad);
            return currentRoad;
        }
        return null;
    }

    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    @Override
    public boolean removeVertex(Town v) {
        Set<Town> towns = graph.keySet();
        if(towns.remove(v))
            return true;
        return false;
    }

    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
    @Override
    public Set<Town> vertexSet() {
        return graph.keySet();
    }

    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
     * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
     * would be in the following format(this is a hypothetical solution):
     * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
     * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
     * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */

    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex);
        LinkedList<Town> path = new LinkedList<>();
        Town next = destinationVertex;

        if (predecessors.get(next) == null) {
            return null;
        }
        path.add(next);
        while (predecessors.get(next) != null) {
            next = predecessors.get(next);
            path.add(next);
        }
        // Reverse the path
        Collections.reverse(path);
        System.out.println(path);
        List<String> result = new ArrayList<>();
        Town start = path.getFirst();
        Town current = null;
        for(int i = 1; i<path.size(); i++){
            current = path.get(i);
            Road r = getEdge(start, current);
            result.add(start+" via "+r.getName()+" to "+current+" "+r.getWeight()+" mi");
            start = current;
        }
        result.add("Total miles: "+distance.get(destinationVertex));
        return (ArrayList<String>) result;
    }

    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     *
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        nodes = new ArrayList<>(graph.keySet());
        edges = new ArrayList<>(edgeSet());
        distance.put(sourceVertex, 0);
        unvisitedTowns.add(sourceVertex);
        while (unvisitedTowns.size() > 0) {
            Town town = getMinimum(unvisitedTowns);
            visitedTowns.add(town);
            unvisitedTowns.remove(town);
            findMinimalDistances(town);
        }
        System.out.println("Distance:::"+distance);
    }

    private void findMinimalDistances(Town town) {
        List<Town> adjacentTowns = getNeighbors(town);
        for (Town destination : adjacentTowns) {
            if (getShortestDistance(destination) > getShortestDistance(town)
                    + getDistance(town, destination)) {
                distance.put(destination, getShortestDistance(town)
                        + getDistance(town, destination));
                predecessors.put(destination, town);
                unvisitedTowns.add(destination);
            }
        }
        System.out.println("Predecessor:::"+predecessors);
    }

    private int getDistance(Town source, Town destination) {
        for (Road road : edges) {
            if (road.getSource().equals(source)
                    && road.getDestination().equals(destination)) {
                return road.getWeight();
            }
        }
        throw new RuntimeException("Runtime exception::: Something went wrong !!!");
    }

    private List<Town> getNeighbors(Town town) {
        List<Town> neighbors = new ArrayList<>();
        for (Road edge : edges) {
            if (edge.getSource().equals(town)
                    && !isVisited(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private boolean isVisited(Town destination) {
        return visitedTowns.contains(destination);
    }

    private Town getMinimum(Set<Town> unvisitedTowns) {
        Town minimum = null;
        for (Town town : unvisitedTowns) {
            if (minimum == null) {
                minimum = town;
            } else {
                if (getShortestDistance(town) < getShortestDistance(minimum)) {
                    minimum = town;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(Town town) {
        Integer d = distance.get(town);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

}