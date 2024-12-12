package Graphs;

import java.util.*;

import Graphs.Graph.Edge;
import Graphs.Graph.Vertex;

public class GraphTraversals {
    private Graph g;

    public GraphTraversals() {
        g = new Graph();
    }

    public GraphTraversals(Graph g) {
        this.g = g;
    }

    // Use a queue to track edges and their end nodes
    public String BFS(String start) {
        if (!g.vertices.containsKey(start))
            return null;
        for (Vertex v : g.vertices.values())
            v.encountered = false;
        StringBuilder sb = new StringBuilder(start).append(" ");
        Queue<Edge> q = new LinkedList<>();
        q.addAll(g.vertices.get(start).edges.values());
        g.vertices.get(start).encountered = true;
        while (!q.isEmpty()) {
            String toAdd = q.poll().endName;
            if (!g.vertices.get(toAdd).encountered) {
                sb.append(toAdd).append(" ");
                g.vertices.get(toAdd).encountered = true;
            }
            for (Edge e : g.vertices.get(toAdd).edges.values())
                if (!g.vertices.get(e.endName).encountered)
                    q.add(e);
        }
        return sb.toString().trim();
    }

    // Use a stack to track vertices
    public String DFS(String start) {
        if (!g.vertices.containsKey(start))
            return null;
        for (Vertex v : g.vertices.values())
            v.encountered = false;
        StringBuilder sb = new StringBuilder();
        Stack<Vertex> s = new Stack<>();
        s.push(g.vertices.get(start));
        g.vertices.get(start).encountered = true;
        while (!s.isEmpty()) {
            Vertex v = s.pop();
            sb.append(v.name).append(" ");
            for (Edge e : v.edges.values()) {
                if (!g.vertices.get(e.endName).encountered) {
                    s.push(g.vertices.get(e.endName));
                    g.vertices.get(e.endName).encountered = true;
                }
            }
        }
        return sb.toString().trim();
    }

    public int shortestPaths(String from) {
        if (from == null)
            return -1;
        if (!g.vertices.containsKey(from))
            return -1;
        for (Vertex v : g.vertices.values()) {
            v.cost = Integer.MAX_VALUE;
            v.done = false;
        }
        return 0;
    }

    public List<String[]> MST() {
        return null;
    }

    public static void main(String[] args) {
        GraphTraversals graph = new GraphTraversals(
                Graph.createGraph(new String[][] {
                        { "A", "B", "2" },
                        { "A", "C", "3" },
                        { "A", "D", "4" },
                        { "C", "D", "3" },
                        { "B", "D", "1" },
                        { "D", "F", "10" }
                }));
        graph.g.printGraph();
        System.out.println(graph.BFS("A"));
        System.out.println(graph.DFS("A"));
        
    }
}
