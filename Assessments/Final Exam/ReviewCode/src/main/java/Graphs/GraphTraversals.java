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

    public List<String[]> shortestPaths(String from) {
        if (from == null)
            return null;
        if (!g.vertices.containsKey(from))
            return null;
        for (Vertex v : g.vertices.values()) {
            v.cost = Integer.MAX_VALUE;
            v.done = false;
        }
        Vertex start = g.vertices.get(from);
        start.cost = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.cost));
        pq.add(start);
        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            if (!current.done) {
                current.done = true;
                for (Edge e : current.edges.values()) {
                    Vertex neighbor = g.vertices.get(e.endName);
                    int newCost = current.cost + e.weight;
                    if (newCost < neighbor.cost) {
                        neighbor.cost = newCost;
                        pq.add(neighbor);
                    }
                }
            }
        }
        ArrayList<String[]> al = new ArrayList<>();
        for (Vertex v : g.vertices.values()) {
            if (!v.name.equals(from)) {
                int weight = (v.cost < 0 || v.cost == Integer.MAX_VALUE) ? -1 : v.cost;
                String to = v.name;
                al.add(new String[] { from, Integer.toString(weight), to });
            }
        }
        return al;
    }

    public List<String[]> MST() {
        if (g.vertices.isEmpty())
            return null;
        for (Vertex v : g.vertices.values())
            v.encountered = false;
        List<String[]> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Vertex start = g.vertices.values().iterator().next();
        start.encountered = true;
        for (Edge e : start.edges.values())
            pq.add(e);
        while (!pq.isEmpty()) {
            Edge smallest = pq.poll();
            Vertex source = g.vertices.get(smallest.startName);
            Vertex destination = g.vertices.get(smallest.endName);
            String weight = Integer.toString(smallest.weight);
            if (!destination.encountered) {
                destination.encountered = true;
                mst.add(new String[] { source.name, destination.name, weight });
                for (Edge e : destination.edges.values())
                    if (!g.vertices.get(e.endName).encountered)
                        pq.add(e);
            }
        }
        for (Vertex v : g.vertices.values())
            if (!v.encountered) return null;
        return mst;
    }

    private static void printList(List<String[]> input) {
        for (String[] arr : input) {
            StringBuilder sb = new StringBuilder();
            for (String entry : arr)
                sb.append(entry).append(" ");
            System.out.println(sb.toString().trim());
        }
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
        System.out.println();
        System.out.println(graph.BFS("A"));
        System.out.println();
        System.out.println(graph.DFS("A"));
        System.out.println();
        List<String[]> dijkstra = graph.shortestPaths("A");
        printList(dijkstra);
        System.out.println();
        List<String[]> prim = graph.MST();
        printList(prim); 
        System.out.println("done");
    }
}
