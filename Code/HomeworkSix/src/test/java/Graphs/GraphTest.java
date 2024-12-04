package Graphs;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The test class for Assignment 6
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class GraphTest {
    Graph testGraph;

    /** Tests the addNode method of the Graph class. */
    @Test
    public void addNodeTest() {
        // Initializations
        testGraph = new Graph();
        String[] toAdd = { "Alfa", "Bravo", "charlie", "Delta", "echo", "Foxtrot" };

        // Test a null node
        assertFalse(testGraph.addNode(null));

        // Test a single node
        assertTrue(testGraph.addNode(toAdd[0]));
        testGraph.getVertices().containsKey(toAdd[0]);

        // Test a duplicate node
        assertFalse(testGraph.addNode(toAdd[0]));

        // Test many nodes added
        for (String s : toAdd)
            testGraph.addNode(s);
        for (String s : toAdd)
            assertTrue(testGraph.getVertices().containsKey(s));
    }

    /** Tests the addEdge method of the Graph class. */
    @Test
    public void addEdgeTest() {
        // Initializations
        testGraph = new Graph();
        String[] toAdd = { "Alfa", "Bravo", "charlie", "Delta", "echo", "Foxtrot" };

        // Check null inputs and a negative weight
        assertFalse(testGraph.addEdge(null, null, -1));
        assertFalse(testGraph.addEdge(null, "null", 0));
        assertFalse(testGraph.addEdge("null", null, 0));
        assertFalse(testGraph.addEdge("null", "null", -2));

        // Add two nodes and try to add an edge between one non-existent node
        testGraph.addNode(toAdd[0]);
        testGraph.addNode(toAdd[1]);
        assertFalse(testGraph.addEdge(toAdd[0], "null", 1));
        assertFalse(testGraph.addEdge("null", toAdd[1], 1));

        // Try to add a self-loop
        assertFalse(testGraph.addEdge(toAdd[0], toAdd[0], 1));
        assertFalse(testGraph.addEdge(toAdd[0], toAdd[0], 2));

        // Add an edge between the two added vertices
        assertTrue(testGraph.addEdge(toAdd[0], toAdd[1], 2));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[1], 2));

        // Add the rest of the nodes and edges
        for (int i = 2; i < toAdd.length; i++)
            testGraph.addNode(toAdd[i]);
        for (int i = 0; i < toAdd.length; i++)
            testGraph.addEdge(toAdd[0], toAdd[i], i);

        // Check the edges existence
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[1], 2));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[2], 2));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[3], 3));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[4], 4));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[5], 5));
    }

    /** Tests the addEdges method of the Graph class. */
    @Test
    public void addEdgesTest() {
        // Initializations
        testGraph = new Graph();
        String[] toAdd = { "Alfa", "Bravo", "charlie", "Delta", "echo", "Foxtrot" };

        // Add all of the nodes
        for (String node : toAdd)
            testGraph.addNode(node);
        
        // Create a toList and a weightList
        String[] toList = { "Bravo", "charlie", "Delta", "echo", "Foxtrot" };
        int[] weightList = { 1, 2, 3, 4, 5 };

        // Try to use method with null inputs
        assertFalse(testGraph.addEdges(null, null, null));
        assertFalse(testGraph.addEdges(null, toList, weightList));
        assertFalse(testGraph.addEdges(toAdd[0], null, weightList));
        assertFalse(testGraph.addEdges(toAdd[0], toList, null));
        
        // Try to use the method with mismatched inputs
        assertFalse(testGraph.addEdges("test", new String[] { "A", "B" }, new int[] { 1, 2, 3 }));
        
        // Try to use the method with an invalid weight in first, middle, & last
        assertFalse(testGraph.addEdges("test", new String[] { "A", "B", "C" }, new int[] { -1, 2, 3 }));
        assertFalse(testGraph.addEdges("test", new String[] { "A", "B", "C" }, new int[] { 1, -2, 3 }));
        assertFalse(testGraph.addEdges("test", new String[] { "A", "B", "C" }, new int[] { 1, 2, -3 }));

        // Add all of the edges
        assertTrue(testGraph.addEdges(toAdd[0], toList, weightList));

        // Check the existence of the edges
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[1], 1));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[2], 2));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[3], 3));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[4], 4));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[5], 5));
    }

    /** Tests the removeNode method of the Graph class. */
    @Test
    public void removeNode() {
        // Initializations
        testGraph = new Graph();

        assertTrue(false);
    }

    /** Tests the removeEdge method of the Graph class. */
    @Test
    public void removeEdge() {
        // Initializations
        testGraph = new Graph();

        assertTrue(false);
    }

    /** Tests the printGraph method of the Graph class. */
    @Test
    public void printGraphTest() {
        // Initializations
        testGraph = new Graph();

        assertTrue(false);
    }

    /** Tests the createGraph method of the Graph class. */
    @Test
    public void createGraphTest() {
        // Initializations
        testGraph = new Graph();

        assertTrue(false);
    }

    /** Tests the shortestDistance method of the Graph class. */
    @Test
    public void shortestDistanceTest() {
        // Initializations
        testGraph = new Graph();

        assertTrue(false);
    }

    /** Tests the minimumSpanningTree method of the Graph class. */
    @Test
    public void minimumSpanningTreeTest() {
        // Initializations
        testGraph = new Graph();

        assertTrue(false);
    }
}