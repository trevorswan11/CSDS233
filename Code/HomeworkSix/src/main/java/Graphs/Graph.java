package Graphs;

import java.util.List;

/**
 * The main Graph class for Assignment 6
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 *
 */
public class Graph {
    // Necessary fields for the Graph
    private String foo;

    /**
     * Creates a new empty Graph with initialized data structures.
     */
    public Graph() {
        
    }

    /**
     * Adds a node to the graph.
     * 
     * @param name The name of the node to insert
     * @return false if the node already exists in the graph
     */
    public boolean addNode(String name) {
        return false;
    }

    /**
     * Adds an undirected edge between two nodes with the given weight.
     * 
     * @param from The first vertex of the edge
     * @param to   The second vertex of the edge
     * @return false if an edge between two nodes already exists, or the weight is
     *         invalid
     */
    public boolean addEdge(String from, String to, int weight) {
        return false;
    }

    /**
     * Adds edges from the
     * specified source node from to each node in the toList array, with
     * corresponding weights
     * provided in the weightList array.
     * 
     * @param from       The source node of all the edges
     * @param toList     the list of nodes to adds edges to from the source node
     * @param weightList the list of weights for the corresponding edges
     * @return false if the lengths fo toList and weightList mismatch, or if any
     *         weight is invalid
     */
    public boolean addEdges(String from, String[] toList, int[] weightList) {
        return false;
    }

    /**
     * Removes a specified node from the graph along with all of its connected edges
     * from the graph.
     * 
     * @param name The name of the node to remove
     * @return false if the node does not exist in the graph
     */
    public boolean removeNode(String node) {
        return false;
    }

    /**
     * Removes the undirected edge between the specified nodes.
     * 
     * @param from The first vertex of the edge
     * @param to   The second vertex of the edge
     * @return false if the node does not exist in the graph
     */
    public boolean removeEdge(String from, String to) {
        return false;
    }

    /**
     * Prints the graph in adjacency list format. The nodes and their neighbors are
     * listed in alphabetical order.
     * 
     * @apiNote Expected format per line: {nodename1} {weight} {neighbor1} {weight}
     *          {neighbor2} ...
     */
    public void printGraph() {
        System.out.println(foo);
    }

    /**
     * Creates a graph from a 2D array of Strings, where each row represents an edge
     * in the format {"source", "destination", "weight"}.
     * 
     * @param input A 2D String array with entries formatted as {}"source",
     *              "destination", "weight"}
     * @return A new Graph if entire input is correct, null otherwise
     */
    public static Graph createGraph(String[][] input) {
        return null;
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
}