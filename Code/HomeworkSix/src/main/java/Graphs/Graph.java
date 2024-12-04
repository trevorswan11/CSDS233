package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The main Graph class for Assignment 6
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class Graph {
    /** The set of vertices for the graph */
    private HashMap<String, Vertex> vertices;

    /**
     * Creates a new empty Graph with initialized data structures.
     */
    public Graph() {
        this.vertices = new HashMap<>();
    }

    /**
     * Adds a node to the graph.
     * 
     * @param name The name of the node to insert
     * @return false if the node already exists in the graph
     */
    public boolean addNode(String name) {
        // Check to see if the vertex already exists in the graph
        if (vertices.containsKey(name))
            return false;

        // Create the vertex and add it to the array
        Vertex newVertex = new Vertex(name);
        this.vertices.put(name, newVertex);
        return true;
    }

    /**
     * Adds an undirected edge between two nodes with the given weight.
     * 
     * @param from   The first vertex of the edge
     * @param to     The second vertex of the edge
     * @param weight The non-negative cost of the edge to add
     * @return false if an edge between two nodes already exists, or the weight is
     *         invalid
     */
    public boolean addEdge(String from, String to, int weight) {
        // Check if either vertex is not in the graph
        if (!this.vertices.containsKey(from))
            return false;
        if (!this.vertices.containsKey(to))
            return false;

        // Check for a self-loop or invalid weight
        if (from.equals(to))
            return false;
        if (weight < 0)
            return false;

        // Check if an edge exists between the vertices
        if (this.vertices.get(from).edges.containsKey(to))
            return false;

        // Create an edge from from to to and to to from as graph is undirected
        vertices.get(from).edges.put(to, new Edge(from, to, weight));
        vertices.get(to).edges.put(from, new Edge(to, from, weight));
        return true;
    }

    /**
     * Adds edges from the specified source node from to each node in the toList
     * array, with corresponding weights provided in the weightList array.
     * 
     * @param from       The source node of all the edges
     * @param toList     the list of nodes to adds edges to from the source node
     * @param weightList the list of weights for the corresponding edges
     * @return false if the lengths fo toList and weightList mismatch, or if any
     *         weight is invalid
     */
    public boolean addEdges(String from, String[] toList, int[] weightList) {
        // Check for length mismatches between toList and weightList
        if (toList.length != weightList.length)
            return false;

        // Check for an invalid weight beforehand to prevent incomplete addition
        for (int weight : weightList)
            if (weight < 0)
                return false;

        // Add all of the edges
        for (int i = 0; i < toList.length; i++)
            this.addEdge(from, toList[i], weightList[i]);
        return true;
    }

    /**
     * Removes a specified node from the graph along with all of its connected edges
     * from the graph.
     * 
     * @param node The name of the node to remove
     * @return false if the node does not exist in the graph
     */
    public boolean removeNode(String node) {
        // Check to see if the node exists
        if (!vertices.containsKey(node))
            return false;

        // Remove all edges pointing to this node
        Vertex toRemove = vertices.get(node);
        for (String neighbor : toRemove.edges.keySet())
            vertices.get(neighbor).removeEdge(node);

        // Remove the node itself
        vertices.remove(node);
        return true;
    }

    /**
     * Removes the undirected edge between the specified nodes.
     * 
     * @param from The first vertex of the edge
     * @param to   The second vertex of the edge
     * @return false if the node does not exist in the graph
     */
    public boolean removeEdge(String from, String to) {
        // Check if either vertex is not in the graph
        if (!this.vertices.containsKey(from))
            return false;
        if (!this.vertices.containsKey(to))
            return false;

        // Remove the from - to edge
        boolean removedFrom = vertices.get(from).removeEdge(to);

        // Remove the to - from edge
        boolean removedTo = vertices.get(to).removeEdge(from);

        // Return true if both edges were removed successfully
        return removedFrom && removedTo;
    }

    /**
     * Prints the graph in adjacency list format. The nodes and their neighbors are
     * listed in alphabetical order.
     * 
     * @apiNote Expected format per line: {nodename1} {weight} {neighbor1} {weight}
     *          {neighbor2} ...
     * @see toString This method returns the printed String representation
     */
    public void printGraph() {
        System.out.println(toString());
    }

    /** Returns the String representation of the graph in adjacency list format */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        // Create a sorted list of vertices using collections
        ArrayList<String> sortedVertices = new ArrayList<>(vertices.keySet());
        Collections.sort(sortedVertices);
        int numVertices = sortedVertices.size();

        // Iterate through the vertices which are now in alpha order
        for (int i = 0; i < numVertices; i++) {
            // Get the current name and vertex object from its name
            String vertexName = sortedVertices.get(i);
            Vertex v = vertices.get(vertexName);

            // Create a string builder for adjacency list formatting
            StringBuilder sb = new StringBuilder(vertexName);

            // Create a sorted list of neighbors
            ArrayList<String> sortedNeighbors = new ArrayList<>(v.edges.keySet());
            Collections.sort(sortedNeighbors);

            // Add each neighbor and its weight to the builder
            for (String neighbor : sortedNeighbors) {
                Edge e = v.edges.get(neighbor);
                sb.append(" ").append(e.weight);
                sb.append(" ").append(neighbor);
            }

            // Append the line to the result, add a newline for all but the last line
            result.append(sb.toString()).append(i == numVertices - 1 ? "" : "\n");
        }
        return result.toString();
    }

    /**
     * Creates a graph from a 2D array of Strings, where each row represents an edge
     * in the format {"source", "destination", "weight"}.
     * 
     * @param input A 2D String array with entries formatted as {"source",
     *              "destination", "weight"}
     * @return A new Graph if entire input is correct, null otherwise
     */
    public static Graph createGraph(String[][] input) {
        // Create the graph to be returned and edge info
        Graph g = new Graph();
        String from, to;
        int weight;

        // Loop through all of the entries in the array
        for (String[] edge : input) {
            // Check if the edge is the correct length
            if (edge.length != 3)
                return null;

            // Parse the inputs of the edge and check its validity
            try {
                from = edge[0];
                to = edge[1];
                weight = Integer.parseInt(edge[2]);
                if (weight < 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                return null;
            }

            // Create the two vertices, methods ignore duplicates by default
            g.addNode(from);
            g.addNode(to);

            // Add the edge between the two vertices
            g.addEdge(from, to, weight);
        }

        return g;
    }

    /**
     * Uses Dijkstra's algorithm to compute the shortest distance between two nodes.
     * 
     * @param from The name of first node
     * @param to   The name of second node
     * @return The shortest distance, otherwise -1 if either node does not exist
     *         or there is no path between them
     */
    public int shortestDistance(String from, String to) {
        // Check if either vertex is not in the graph
        if (!this.vertices.containsKey(from))
            return -1;
        if (!this.vertices.containsKey(to))
            return -1;

        // Reset the cost, done status, and parent status for each vertex
        for (Vertex v : this.vertices.values()) {
            v.cost = Integer.MAX_VALUE;
            v.done = false;
        }

        // Use a priority queue to store the vertices by cost
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.cost));

        // Set the starting vertex's cost to 0 and add it to the priority queue
        this.vertices.get(from).cost = 0;
        pq.add(this.vertices.get(from));

        // Loop until the queue is empty
        while (!pq.isEmpty()) {
            // Get the current vertex and skip if its been finished
            Vertex current = pq.poll();
            if (!current.done) {
                current.done = true;
                // Loop through all of the vertex's edges
                for (Edge edge : current.edges.values()) {
                    // Get currents neighbor on the edge and calculate its cost
                    Vertex neighbor = this.vertices.get(edge.endName);
                    int newCost = current.cost + edge.weight;

                    // Only update the cost if the path is cheaper
                    if (newCost < neighbor.cost) {
                        neighbor.cost = newCost;
                        pq.add(neighbor);
                    }
                }
            }
        }

        // If the cost is not MAX_VALUE, it can be returned, otherwise its -1
        int finalCost = this.vertices.get(to).cost;
        return finalCost == Integer.MAX_VALUE ? -1 : finalCost;
    }

    /**
     * Uses Prim's algorithm to compute the Minimum Spanning Tree of the graph.
     * 
     * @return a List<String[]> where each String[] represents an edge in the format
     *         {source, destination, weight}. The edges can be
     *         returned in any order. If the graph is disconnected, the method
     *         returns null.
     */
    public List<String[]> minimumSpanningTree() {
        // Check if the graph is empty
        if (this.vertices.isEmpty())
            return null;

        // Reset the encountered status for every vertex
        for (Vertex v : this.vertices.values())
            v.encountered = false;

        // Create the mst object and also the priority queue for traversals
        List<String[]> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        // Get an arbitrary vertex to start with and mark it as seen
        Vertex start = this.vertices.values().iterator().next();
        start.encountered = true;

        // Add all of the starting vertex's edges to the pq
        for (Edge e : start.edges.values())
            pq.add(e);

        // Process the edges until the pq is empty
        while (!pq.isEmpty()) {
            // Get the smallest edge and its endNode
            Edge smallestEdge = pq.poll();
            Vertex source = this.vertices.get(smallestEdge.startName);
            Vertex destination = this.vertices.get(smallestEdge.endName);
            String weight = String.valueOf(smallestEdge.weight);

            // Only process the edge if the vertex hasn't been encountered
            if (!destination.encountered) {
                // Mark as encountered, then add edge to the list
                destination.encountered = true;
                mst.add(new String[] { source.name, destination.name, weight });

                // Add all edges of the destination to the pq
                for (Edge e : destination.edges.values())
                    if (!this.vertices.get(e.endName).encountered)
                        pq.add(e);
            }
        }

        // Check if all vertices have been encountered (is graph connected?)
        for (Vertex v : this.vertices.values())
            if (!v.encountered)
                return null;
        return mst;
    }

    /**
     * Determines the minimum spanning tree for the graph and returns it as a
     * formatted String for testing
     * 
     * @return The minimum spanning tree for the graph, where each line is in the
     *         form {source, destination, weight}
     */
    public String mstToString() {
        // Create a string builder and determine the graphs MST
        StringBuilder sb = new StringBuilder();
        List<String[]> myMST = this.minimumSpanningTree();

        // Create an iterator for the list for easy linear access, and loop
        Iterator<String[]> itr = myMST.iterator();
        while (itr.hasNext()) {
            // Add the next item and only newline if theres a succeeding one
            sb.append(Arrays.toString(itr.next()));
            sb.append(itr.hasNext() ? "\n" : "");
        }
        return sb.toString();
    }

    /**
     * A class to represent a vertex in a Graph
     * 
     * @author Trevor Swan
     * @version CSDS233 - Fall 2024
     */
    private class Vertex {
        /** The name of the vertex */
        private String name;
        /** The edges of the vertex as a Map */
        private HashMap<String, Edge> edges;
        /** Indicates whether the vertex has been viewed by dijkstra's algorithm */
        private boolean encountered;
        /** Indicates whether the vertex has been included in the MST */
        private boolean done;
        /** The cost of the vertex */
        private int cost;

        /**
         * Creates a new vertex with a custom name
         * 
         * @param name The desired name of the vertex
         */
        public Vertex(String name) {
            this.name = name;
            this.edges = new HashMap<>();
            this.encountered = false;
            this.done = false;
            this.cost = Integer.MAX_VALUE;
        }

        /**
         * Removes an edge to the given vertex
         * 
         * @param neighbor The connected vertex by the edge
         * @return True if the edge is removed successfully
         */
        private boolean removeEdge(String neighbor) {
            return edges.remove(neighbor) != null;
        }
    }

    /**
     * A class to represent an edge in a Graph
     * 
     * @author Trevor Swan
     * @version CSDS233 - Fall 2024
     */
    private class Edge {
        /** The vertex at the start of the edge */
        private String startName;
        /** The vertex at the end of the edge */
        private String endName;
        /** The cost of the edge */
        private int weight;

        /**
         * Creates a new edge pointing to a new Vertex
         * 
         * @param endNode The location of the node in the vertex array
         * @param weight  The cost of the edge to add
         */
        public Edge(String startName, String endName, int weight) {
            this.startName = startName;
            this.endName = endName;
            this.weight = weight;
        }
    }
}