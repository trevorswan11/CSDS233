package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
    public HashMap<String, Vertex> vertices;
    public HashMap<String, Vertex> getVertices() {
        return this.vertices;
    }
    public Graph() {
        this.vertices = new HashMap<>();
    }
    public boolean hasEdge(String from, String to, int weight) {
        if (!this.vertices.containsKey(from) || !this.vertices.containsKey(to) || weight < 0)
            return false;
        Vertex v1 = this.vertices.get(from);
        Vertex v2 = this.vertices.get(to);
        return v1.hasEdge(to, weight) && v2.hasEdge(from, weight);
    }
    public boolean hasEdge(String from, String to) {
        if (!this.vertices.containsKey(from) || !this.vertices.containsKey(to))
            return false;
        Vertex v1 = this.vertices.get(from);
        Vertex v2 = this.vertices.get(to);
        return v1.hasEdge(to) && v2.hasEdge(from);
    }
    public boolean addNode(String name) {
        if (name == null)
            return false;
        if (vertices.containsKey(name))
            return false;
        Vertex newVertex = new Vertex(name);
        this.vertices.put(name, newVertex);
        return true;
    }
    public boolean addEdge(String from, String to, int weight) {
        if (from == null || to == null || weight < 0)
            return false;
        if (!this.vertices.containsKey(from))
            return false;
        if (!this.vertices.containsKey(to))
            return false;
        if (from.equals(to))
            return false;
        if (this.vertices.get(from).edges.containsKey(to))
            return false;
        vertices.get(from).edges.put(to, new Edge(from, to, weight));
        vertices.get(to).edges.put(from, new Edge(to, from, weight));
        return true;
    }
    public boolean addEdges(String from, String[] toList, int[] weightList) {
        if (from == null || toList == null || weightList == null)
            return false;
        if (toList.length != weightList.length)
            return false;
        for (int weight : weightList)
            if (weight < 0)
                return false;
        for (int i = 0; i < toList.length; i++)
            this.addEdge(from, toList[i], weightList[i]);
        return true;
    }
    public boolean removeNode(String node) {
        if (node == null)
            return false;
        if (!vertices.containsKey(node))
            return false;
        Vertex toRemove = vertices.get(node);
        for (String neighbor : toRemove.edges.keySet())
            vertices.get(neighbor).removeEdge(node);
        vertices.remove(node);
        return true;
    }
    public boolean removeEdge(String from, String to) {
        if (from == null || to == null)
            return false;
        if (!this.vertices.containsKey(from))
            return false;
        if (!this.vertices.containsKey(to))
            return false;
        boolean removedFrom = vertices.get(from).removeEdge(to);
        boolean removedTo = vertices.get(to).removeEdge(from);
        return removedFrom && removedTo;
    }
    public void printGraph() {
        System.out.println(toString());
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        ArrayList<String> sortedVertices = new ArrayList<>(vertices.keySet());
        Collections.sort(sortedVertices, String.CASE_INSENSITIVE_ORDER);
        int numVertices = sortedVertices.size();
        for (int i = 0; i < numVertices; i++) {
            String vertexName = sortedVertices.get(i);
            Vertex v = vertices.get(vertexName);
            StringBuilder sb = new StringBuilder(vertexName);
            ArrayList<String> sortedNeighbors = new ArrayList<>(v.edges.keySet());
            Collections.sort(sortedNeighbors, String.CASE_INSENSITIVE_ORDER);
            for (String neighbor : sortedNeighbors) {
                Edge e = v.edges.get(neighbor);
                sb.append(" ").append(e.weight);
                sb.append(" ").append(neighbor);
            }
            result.append(sb.toString()).append(i == numVertices - 1 ? "" : "\n");
        }
        return result.toString();
    }
    public static Graph createGraph(String[][] input) {
        if (input == null)
            return null;
        Graph g = new Graph();
        String from, to;
        int weight;
        for (String[] edge : input) {
            if (edge.length != 3)
                return null;
            try {
                from = edge[0];
                to = edge[1];
                weight = Integer.parseInt(edge[2]);
                if (weight < 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                return null;
            }
            g.addNode(from);
            g.addNode(to);
            g.addEdge(from, to, weight);
        }
        return g;
    }

    public int shortestDistance(String from, String to) {
        if (from == null || to == null)
            return -1;
        if (!this.vertices.containsKey(from))
            return -1;
        if (!this.vertices.containsKey(to))
            return -1;
        if (from.equals(to))
            return 0;
        for (Vertex v : this.vertices.values()) {
            v.cost = Integer.MAX_VALUE;
            v.done = false;
        }
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.cost));
        this.vertices.get(from).cost = 0;
        pq.add(this.vertices.get(from));
        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            if (!current.done) {
                current.done = true;
                for (Edge edge : current.edges.values()) {
                    Vertex neighbor = this.vertices.get(edge.endName);
                    int newCost = current.cost + edge.weight;
                    if (newCost < neighbor.cost) {
                        neighbor.cost = newCost;
                        pq.add(neighbor);
                    }
                }
            }
        }
        int finalCost = this.vertices.get(to).cost;
        return finalCost == Integer.MAX_VALUE ? -1 : finalCost;
    }
    public List<String[]> minimumSpanningTree() {
        if (this.vertices.isEmpty())
            return null;
        for (Vertex v : this.vertices.values())
            v.encountered = false;
        List<String[]> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Vertex start = this.vertices.values().iterator().next();
        start.encountered = true;
        for (Edge e : start.edges.values())
            pq.add(e);
        while (!pq.isEmpty()) {
            Edge smallestEdge = pq.poll();
            Vertex source = this.vertices.get(smallestEdge.startName);
            Vertex destination = this.vertices.get(smallestEdge.endName);
            String weight = String.valueOf(smallestEdge.weight);
            if (!destination.encountered) {
                destination.encountered = true;
                mst.add(new String[] { source.name, destination.name, weight });
                for (Edge e : destination.edges.values())
                    if (!this.vertices.get(e.endName).encountered)
                        pq.add(e);
            }
        }
        for (Vertex v : this.vertices.values())
            if (!v.encountered)
                return null;
        return mst;
    }

    public String mstToString() {
        StringBuilder sb = new StringBuilder();
        List<String[]> myMST = this.minimumSpanningTree();

        Iterator<String[]> itr = myMST.iterator();
        while (itr.hasNext()) {
            sb.append(Arrays.toString(itr.next()));
            sb.append(itr.hasNext() ? "\n" : "");
        }
        return sb.toString();
    }
    public class Vertex {
        public String name;
        public HashMap<String, Edge> edges;
        public boolean encountered;
        public boolean done;
        public int cost;
        public Vertex(String name) {
            this.name = name;
            this.edges = new HashMap<>();
            this.encountered = false;
            this.done = false;
            this.cost = Integer.MAX_VALUE;
        }
        private boolean hasEdge(String to, int weight) {
            if (to == null || weight < 0 || !this.edges.containsKey(to))
                return false;
            Edge e = this.edges.get(to);
            return e.endName.equals(to) && e.weight == weight;
        }
        private boolean hasEdge(String to) {
            if (to == null || !this.edges.containsKey(to))
                return false;
            Edge e = this.edges.get(to);
            return e.endName.equals(to);
        }
        private boolean removeEdge(String neighbor) {
            return edges.remove(neighbor) != null;
        }
    }
    public class Edge {
        public String startName;
        public String endName;
        public int weight;
        public Edge(String startName, String endName, int weight) {
            this.startName = startName;
            this.endName = endName;
            this.weight = weight;
        }
    }
}
