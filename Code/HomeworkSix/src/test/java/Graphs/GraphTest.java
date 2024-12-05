package Graphs;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        assertTrue(testGraph.getVertices().containsKey(toAdd[0]));

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
    public void removeNodeTest() {
        // Initializations
        testGraph = new Graph();
        String[] toAdd = { "Alfa", "Bravo", "charlie", "Delta", "echo", "Foxtrot" };

        // Add all of the nodes
        for (String node : toAdd)
            testGraph.addNode(node);

        // Add a couple edges
        testGraph.addEdge(toAdd[0], toAdd[4], 1);
        testGraph.addEdge(toAdd[2], toAdd[3], 1);
        testGraph.addEdge(toAdd[2], toAdd[4], 1);
        testGraph.addEdge(toAdd[5], toAdd[4], 1);

        // Remove a null node and one that doesn't exist
        assertFalse(testGraph.removeNode(null));
        assertFalse(testGraph.removeNode("null"));

        // Remove a node with no edges
        assertTrue(testGraph.removeNode(toAdd[1]));
        assertFalse(testGraph.getVertices().containsKey(toAdd[1]));
        assertTrue(testGraph.hasEdge(toAdd[0], toAdd[4], 1));
        assertTrue(testGraph.hasEdge(toAdd[2], toAdd[3], 1));
        assertTrue(testGraph.hasEdge(toAdd[2], toAdd[4], 1));

        // Remove a node with one edge
        assertTrue(testGraph.removeNode(toAdd[0]));
        assertFalse(testGraph.getVertices().containsKey(toAdd[0]));
        assertFalse(testGraph.hasEdge(toAdd[0], toAdd[4]));
        assertTrue(testGraph.hasEdge(toAdd[5], toAdd[4], 1));
        assertTrue(testGraph.hasEdge(toAdd[2], toAdd[3], 1));
        assertTrue(testGraph.hasEdge(toAdd[2], toAdd[4], 1));

        // Remove a node with two edges
        testGraph.removeNode(toAdd[2]);
        assertFalse(testGraph.getVertices().containsKey(toAdd[2]));
        assertFalse(testGraph.hasEdge(toAdd[2], toAdd[3]));
        assertFalse(testGraph.hasEdge(toAdd[2], toAdd[4]));
        assertTrue(testGraph.hasEdge(toAdd[5], toAdd[4], 1));
    }

    /** Tests the removeEdge method of the Graph class. */
    @Test
    public void removeEdgeTest() {
        // Initializations
        testGraph = new Graph();
        String[] toAdd = { "Alfa", "Bravo", "charlie", "Delta", "echo", "Foxtrot" };

        // Add all of the nodes
        for (String node : toAdd)
            testGraph.addNode(node);

        // Add a couple edges
        testGraph.addEdge(toAdd[0], toAdd[4], 1);
        testGraph.addEdge(toAdd[2], toAdd[3], 1);
        testGraph.addEdge(toAdd[2], toAdd[4], 1);
        testGraph.addEdge(toAdd[5], toAdd[4], 1);

        // Remove a null edge and one that doesn't exist
        assertFalse(testGraph.removeEdge(null, null));
        assertFalse(testGraph.removeEdge(null, toAdd[4]));
        assertFalse(testGraph.removeEdge("null", toAdd[4]));
        assertFalse(testGraph.removeEdge(toAdd[2], null));
        assertFalse(testGraph.removeEdge(toAdd[2], "null"));

        // Remove the 0 - 4 edge (in terms of toAdd indices)
        assertTrue(testGraph.removeEdge(toAdd[0], toAdd[4]));
        assertFalse(testGraph.hasEdge(toAdd[0], toAdd[4]));
        assertFalse(testGraph.hasEdge(toAdd[4], toAdd[0]));
        assertTrue(testGraph.getVertices().containsKey(toAdd[0]));
        assertTrue(testGraph.getVertices().containsKey(toAdd[4]));

        // Remove the 2 - 3 edge (in terms of toAdd indices)
        assertTrue(testGraph.removeEdge(toAdd[2], toAdd[3]));
        assertFalse(testGraph.hasEdge(toAdd[2], toAdd[3]));
        assertFalse(testGraph.hasEdge(toAdd[3], toAdd[2]));
        assertTrue(testGraph.getVertices().containsKey(toAdd[2]));
        assertTrue(testGraph.getVertices().containsKey(toAdd[3]));
    }

    /**
     * Tests the printGraph method of the Graph class using toString.
     * ! Only use single word nodes for correct testing.
     */
    @Test
    public void printGraphTest() {
        // Initializations
        testGraph = new Graph();
        String[] toAdd = { "Alfa", "Bravo", "charlie", "Delta", "echo", "Foxtrot" };

        // Add all of the nodes
        for (String node : toAdd)
            testGraph.addNode(node);

        // Add a couple edges
        testGraph.addEdge(toAdd[0], toAdd[4], 1);
        testGraph.addEdge(toAdd[2], toAdd[3], 1);
        testGraph.addEdge(toAdd[2], toAdd[4], 1);
        testGraph.addEdge(toAdd[5], toAdd[4], 1);

        // Get the String representation of the graph
        String graphToString = testGraph.toString();

        // Use regex (had to look it up) to split into lines
        String[] lines = graphToString.split("\\R");
        ArrayList<String> firstWords = new ArrayList<>();

        // Make each entry in lines drop the edge weights
        lines = Arrays.stream(lines)
                .map(GraphTest::removeEveryOtherWord)
                .toArray(String[]::new);

        // Go through each line
        for (String line : lines) {
            String[] words = line.split(" ");

            // Add the first word in each line to the ArrayList
            firstWords.add(words[0]);

            // Use helper method to check if its alphabetical
            assertTrue(GraphTest.isAlphabeticallySorted(words));
        }

        // Convert the array list to a String array
        String[] firstWordsARR = firstWords.toArray(new String[0]);

        // Sort the ArrayList and compare it to the array
        Collections.sort(firstWords, String.CASE_INSENSITIVE_ORDER);
        String[] firstWordsARRSorted = firstWords.toArray(new String[0]);
        assertTrue(Arrays.deepEquals(firstWordsARR, firstWordsARRSorted));

        // Print the graph
        testGraph.printGraph();
    }

    /**
     * Removes every other word from a String starting at the second word.
     * 
     * @param line The String to work on
     * @return The line with every odd indexed word removed
     */
    private static String removeEveryOtherWord(String line) {
        // Split the line into its words
        String[] words = line.split(" ");
        StringBuilder stripped = new StringBuilder();

        // Loop through all of the words from the line
        for (int i = 0; i < words.length; i++) {
            // Only keep even indexed words
            if (i % 2 == 0) {
                if (stripped.length() > 0)
                    stripped.append(" ");
                stripped.append(words[i]);
            }
        }

        // Return the filtered line
        return stripped.toString();
    }

    /**
     * Checks if the neighboring vertices of a node are sorted in alphabetical order
     * 
     * @param words A String array of words only
     * @return True if and only if all vertices from index 1 to the end of the list
     *         are alphabetically sorted. This ignores the first word in the String
     *         array
     */
    private static boolean isAlphabeticallySorted(String[] words) {
        // Length 2 must be sorted with respect to the head
        if (words.length <= 2)
            return true;

        // Loop through the list and compare subsequent words
        for (int i = 1; i < words.length - 1; i++) {
            if (words[i].compareToIgnoreCase(words[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /** Tests the createGraph method of the Graph class. */
    @Test
    public void createGraphTest() {
        // Initialization with null input
        testGraph = Graph.createGraph(null);
        assertNull(testGraph);

        // Initialization with incorrect element length
        testGraph = Graph.createGraph(new String[][] {
                { "A", "B" },
                { "A", "C", "3" },
                { "A", "D", "4" },
                { "C", "D", "3" },
                { "B", "D", "1" },
                { "D", "F", "10" }
        });
        assertNull(testGraph);

        // Initialization with incorrect edge weight (negative)
        testGraph = Graph.createGraph(new String[][] {
                { "A", "B", "2" },
                { "A", "C", "3" },
                { "A", "D", "4" },
                { "C", "D", "-3" },
                { "B", "D", "1" },
                { "D", "F", "10" }
        });
        assertNull(testGraph);

        // Initialization with incorrect edge weight (non-numeric)
        testGraph = Graph.createGraph(new String[][] {
                { "A", "B", "2" },
                { "A", "C", "3" },
                { "A", "D", "4" },
                { "C", "D", "E" },
                { "B", "D", "1" },
                { "D", "F", "10" }
        });
        assertNull(testGraph);

        // Correct Initialization
        testGraph = Graph.createGraph(new String[][] {
                { "A", "B", "2" },
                { "A", "C", "3" },
                { "A", "D", "4" },
                { "C", "D", "3" },
                { "B", "D", "1" },
                { "D", "F", "10" }
        });

        // Check if all of the edges are there
        assertTrue(testGraph.hasEdge("A", "B", 2));
        assertTrue(testGraph.hasEdge("A", "C", 3));
        assertTrue(testGraph.hasEdge("A", "D", 4));
        assertTrue(testGraph.hasEdge("C", "D", 3));
        assertTrue(testGraph.hasEdge("B", "D", 1));
        assertTrue(testGraph.hasEdge("D", "F", 10));
        assertFalse(testGraph.hasEdge("A", "F"));
    }

    /** Tests the shortestDistance method (both directions) of the Graph class. */
    @Test
    public void shortestDistanceTest() {
        // Initializations - Connected graph with one disconnected component
        testGraph = Graph.createGraph(new String[][] {
                { "A", "B", "2" },
                { "A", "C", "3" },
                { "A", "D", "4" },
                { "C", "D", "3" },
                { "B", "D", "1" },
                { "D", "F", "10" }
        });
        testGraph.addNode("G");

        // Test with null inputs and inputs not in the graph
        assertEquals(-1, testGraph.shortestDistance(null, null));
        assertEquals(-1, testGraph.shortestDistance(null, "A"));
        assertEquals(-1, testGraph.shortestDistance("B", null));
        assertEquals(-1, testGraph.shortestDistance("X", "Z"));
        assertEquals(-1, testGraph.shortestDistance("X", "A"));
        assertEquals(-1, testGraph.shortestDistance("B", "Z"));

        // Test a route with one edge in between
        assertEquals(2, testGraph.shortestDistance("A", "B"));
        assertEquals(2, testGraph.shortestDistance("B", "A"));

        // Test a route with two edges in between
        assertEquals(11, testGraph.shortestDistance("B", "F"));
        assertEquals(11, testGraph.shortestDistance("F", "B"));

        // Test a route with multiple edges in between
        assertEquals(13, testGraph.shortestDistance("A", "F"));
        assertEquals(13, testGraph.shortestDistance("F", "A"));

        // Test a route with a disconnected node
        assertEquals(-1, testGraph.shortestDistance("A", "G"));
        assertEquals(-1, testGraph.shortestDistance("G", "A"));

        // Test a route to itself
        assertEquals(0, testGraph.shortestDistance("A", "A"));
    }

    /** Tests the minimumSpanningTree method of the Graph class. */
    @Test
    public void minimumSpanningTreeTest() {
        List<String[]> expected, actual;

        // Initialization 1 - Empty Graph
        testGraph = new Graph();
        assertNull(testGraph.minimumSpanningTree());

        // Init 2 - Small Connected Graph (1 vertex)
        testGraph = Graph.createGraph(new String[][] {
                { "A", "B", "2" }
        });
        actual = testGraph.minimumSpanningTree();
        expected = new ArrayList<>();
        expected.add(new String[] { "A", "B", "2" });
        assertTrue(GraphTest.deepMSTEquals(expected, actual));

        // Init 3 - Medium Connected Graph (3 vertices)
        testGraph = Graph.createGraph(new String[][] {
                { "A", "B", "2" },
                { "A", "C", "3" },
                { "A", "D", "4" }
        });
        actual = testGraph.minimumSpanningTree();
        expected = new ArrayList<>();
        expected.add(new String[] { "A", "B", "2" });
        expected.add(new String[] { "A", "C", "3" });
        expected.add(new String[] { "A", "D", "4" });
        assertTrue(GraphTest.deepMSTEquals(expected, actual));

        // Init 4 - Large Connected Graph (6 vertices)
        testGraph = Graph.createGraph(new String[][] {
                { "A", "B", "2" },
                { "A", "C", "3" },
                { "A", "D", "4" },
                { "C", "D", "3" },
                { "B", "D", "1" },
                { "D", "e", "10" }
        });
        actual = testGraph.minimumSpanningTree();
        expected = new ArrayList<>();
        expected.add(new String[] { "A", "B", "2" });
        expected.add(new String[] { "A", "C", "3" });
        expected.add(new String[] { "D", "e", "10" });
        expected.add(new String[] { "B", "D", "1" });
        assertTrue(GraphTest.deepMSTEquals(expected, actual));

        // Add a disconnected component and check
        testGraph.addNode("G");
        assertNull(testGraph.minimumSpanningTree());
    }

    /**
     * Checks the equality of two List objects containing String arrays by comparing
     * each element in the Arrays. The check is independent of order.
     * 
     * @param expected The expected result
     * @param actual   The actual result
     * @return True if the expected and actual result are exactly equal with respect
     *         to their contents
     */
    private static boolean deepMSTEquals(List<String[]> expected, List<String[]> actual) {
        // The size of the Lists must match!
        if (expected.size() != actual.size())
            return false;

        // Convert each String[] to a List<String> and collect into sets
        Set<List<String>> expectedSet = expected.stream()
                .map(Arrays::asList)
                .collect(Collectors.toSet());
        Set<List<String>> actualSet = actual.stream()
                .map(Arrays::asList)
                .collect(Collectors.toSet());

        // Compare the sets
        return expectedSet.equals(actualSet);
    }

    /** Tests and prints the example from the instructions */
    @Test
    public void assignmentExampleTest() {
        List<String[]> expected, actual;

        // Create the example graph
        Graph exampleGraph = Graph.createGraph(new String[][] {
                { "U", "V", "2" },
                { "U", "X", "1" },
                { "V", "W", "3" },
                { "V", "X", "2" },
                { "U", "W", "5" },
                { "W", "Y", "1" },
                { "W", "Z", "5" },
                { "X", "Y", "1" },
                { "Y", "Z", "2" },
                { "W", "X", "3" }
        });

        // Get the String representation of the graph
        StringBuilder sb = new StringBuilder();
        sb.append("U 2 V 5 W 1 X").append("\n");
        sb.append("V 2 U 3 W 2 X").append("\n");
        sb.append("W 5 U 3 V 3 X 1 Y 5 Z").append("\n");
        sb.append("X 1 U 2 V 3 W 1 Y").append("\n");
        sb.append("Y 1 W 1 X 2 Z").append("\n"); 
        sb.append("Z 5 W 2 Y");
        String examplePrintedGraph = sb.toString();

        // Compare the actual representation to what was given
        assertEquals(examplePrintedGraph, exampleGraph.toString());

        // Get the minimum spanning tree for the graph
        actual = exampleGraph.minimumSpanningTree();

        // Add the example output to the expected list for comparison
        expected = new ArrayList<>();
        String[][] exampleMSTOutput = {
            {"U", "X", "1"}, 
            {"U", "V", "2"}, 
            {"X", "Y", "1"}, 
            {"Y", "W", "1"}, 
            {"Y", "Z", "2"}
        };
        for (String[] arr : exampleMSTOutput)
            expected.add(arr);

        // Compare the two results
        assertTrue(GraphTest.deepMSTEquals(expected, actual));
    }
}