package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
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
        vertices.get(from).edges.put(to, new Edge(to, weight));
        vertices.get(to).edges.put(from, new Edge(from, weight));
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
        // Create the graph to be returned
        Graph g = new Graph();

        // Loop through all of the entries in the array
        for (String[] edge : input) {
            // Check if the edge is the correct length
            if (edge.length != 3) return null;
        }

        return g;
    }

    /**
     * Uses Dijkstra's algorithm to compute the shortest distance between two nodes.
     * 
     * @param from The name of first node
     * @param to   The name of second node
     * @return The shortest distance, otherwise -1 if a either node does not exist
     *         or there is no path between them
     */
    public int shortestDistance(String from, String to) {
        return -1;
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
        return null;
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
        /** The parent vertex of the current vertex */
        private Vertex parent;
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
            this.parent = null;
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
        public Edge(String endName, int weight) {
            this.endName = endName;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode("poop");
        g.addNode("poop1");
        g.addNode("poop2");
        g.addNode("poop3");
        g.addNode("poop4");

        String[] toS = { "poop1", "poop2", "poop3", "poop4" };
        int[] weights = { 1, 2, 3, 4 };
        g.addEdges("poop", toS, weights);
        g.printGraph();
        System.out.println("ok");
    }
}