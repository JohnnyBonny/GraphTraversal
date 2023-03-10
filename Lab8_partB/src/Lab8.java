/* I created a node class that attributes were a name and a color. In order to create the graph using
the nodes, I decided to make the graph by using a Map. The key represents the vertex, and its values
is a List of vertices. The value of the map represents all the values that are adjacent to its key.*/
import java.util.*;

class graph {
    private Map<vertex,List<vertex>> mainGraph;
    private int nodeNr;
    public String[] characters= {"a","b","c","d","e","f","g"};

    graph(int order){
        nodeNr = order;
        mainGraph = new HashMap<>();

        for(int i = 0; i < nodeNr;i++){
            mainGraph.put(new vertex(),new ArrayList<>());
        }
    }

    graph createGraphG1(int order){ //creates the same graph as the one in the example
        graph G1 = new graph(order);
        G1.addVertex("a");
        G1.addVertex("b");
        G1.addVertex("c");
        G1.addVertex("d");
        G1.addVertex("e");
        G1.addVertex("f");
        G1.addVertex("g");

        G1.addEdge("a", "b");
        G1.addEdge("a", "d");
        G1.addEdge("a", "c");
        G1.addEdge("b", "d");
        G1.addEdge("c", "d");
        G1.addEdge("d", "e");
        G1.addEdge("f", "e");
        G1.addEdge("e", "g");

        return G1;
    }

    void addVertex(String input){
        vertex dummy = new vertex();
        dummy.setName(input);
        mainGraph.putIfAbsent(dummy,new ArrayList<>());
    }

    void addEdge(String vertex1, String vertex2) {
        vertex Vertex1 = new vertex();
        vertex Vertex2 = new vertex();
        Vertex1.setName(vertex1);
        Vertex2.setName(vertex2);
        mainGraph.get(Vertex1).add(Vertex2);
    }

    List<vertex> getAdj(String name) {
        vertex dummy = new vertex();
        dummy.setName(name);
        return mainGraph.get(dummy);
    }

    public int getNodeNr() {
        return nodeNr;
    }

    public String getVertexSet() {
        Set<vertex> keys = mainGraph.keySet();
        keys.toArray();
        return String.valueOf(keys);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        graph graph = (graph) o;
        return nodeNr == graph.nodeNr &&
                Objects.equals(mainGraph, graph.mainGraph) &&
                Arrays.equals(characters, graph.characters);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mainGraph, nodeNr);
        result = 31 * result + Arrays.hashCode(characters);
        return result;
    }
}

public class Lab8 {
    public static int time;
    public static LinkedList<vertex> vertex;

    static void printListG1(graph graph) {
        String[] characters = {"a", "b", "c", "d", "e", "f", "g"};
        for (int i = 0; i < graph.getNodeNr(); i++) {
            System.out.print(characters[i] + " -> ");
            for (vertex x : graph.getAdj(characters[i])) {
                System.out.print(x.toString());
            }
            System.out.println();
        }
    }

    static void DFS(graph Graph) {
        for (String x : Graph.getVertexSet()) { // checks all vertices
            vertex y = new vertex();
            y.setName(x);
            //System.out.println("vertex " + x + " parent: " + x.getParent()+ " " + Graph.getAdj(x.getName()));
            if(y == null && !y.getName().equals(" ")){
                x.setParent(x);
                DFS_visit(x, Graph);
            }
        }
    }


    static void DFS_visit(vertex u, graph graph){ // DFS in lecture
        time++;
        u.setStart(time);
        System.out.println(u.getName());
        System.out.println(graph.getAdj(u.getName()));
        /*
        for (vertex i : graph.getAdj(u.getName())) {
            if (i.getStart() == 0) {
                i.setParent(u);
                DFS_visit(u, graph);
            }
            if (i.getStart() != 0 && i.getEnd() == 0)
                System.out.println("Back edge found");
        }

         */
        time++;
        u.setEnd(time);
        //System.out.println("vertex " + u + " parent: " + u.getParent());
    }






    public static void main(String args[]) {
        graph G1 = new graph(7);

        G1 = G1.createGraphG1(7);

        printListG1(G1);
        DFS(G1);

    }
}

/*
import java.util.*;

class graph {
    private Map<vertex,List<vertex>> mainGraph;
    private int nodeNr;
    public String[] characters= {"a","b","c","d","e","f","g"};

    graph(int order){
        nodeNr = order;
        mainGraph = new HashMap<>();

        for(int i = 0; i < nodeNr;i++){
            mainGraph.put(new vertex(),new ArrayList<>());
        }
    }

    graph createGraphG1(int order){ //creates the same graph as the one in the example
        graph G1 = new graph(order);
        G1.addVertex("a");
        G1.addVertex("b");
        G1.addVertex("c");
        G1.addVertex("d");
        G1.addVertex("e");
        G1.addVertex("f");
        G1.addVertex("g");

        G1.addEdge("a", "b");
        G1.addEdge("a", "d");
        G1.addEdge("a", "c");
        G1.addEdge("b", "d");
        G1.addEdge("c", "d");
        G1.addEdge("d", "e");
        G1.addEdge("f", "e");
        G1.addEdge("e", "g");

        return G1;
    }

    void addVertex(String input){
        vertex dummy = new vertex();
        dummy.setName(input);
        mainGraph.putIfAbsent(dummy,new ArrayList<>());
    }

    void addEdge(String vertex1, String vertex2) {
        vertex Vertex1 = new vertex();
        vertex Vertex2 = new vertex();
        Vertex1.setName(vertex1);
        Vertex2.setName(vertex2);
        mainGraph.get(Vertex1).add(Vertex2);
    }

    List<vertex> getAdj(String name) {
        vertex dummy = new vertex();
        dummy.setName(name);
        return mainGraph.get(dummy);
    }

    public int getNodeNr() {
        return nodeNr;
    }

    public Set<vertex> getVertexSet() {
        return mainGraph.keySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        graph graph = (graph) o;
        return nodeNr == graph.nodeNr &&
                Objects.equals(mainGraph, graph.mainGraph) &&
                Arrays.equals(characters, graph.characters);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mainGraph, nodeNr);
        result = 31 * result + Arrays.hashCode(characters);
        return result;
    }
}

public class Lab8 {
    public static int time;
    public static LinkedList<vertex> G1vertex;
    public static LinkedList<vertex> G2vertex;

    static void printListG1(graph graph) {
        String[] characters = {"a", "b", "c", "d", "e", "f", "g"};
        for (int i = 0; i < graph.getNodeNr(); i++) {
            System.out.print(characters[i] + " -> ");
            for (vertex x : graph.getAdj(characters[i])) {
                System.out.print(x.toString());
            }
            System.out.println();
        }
    }

    static void DFS(graph Graph) {
        for (vertex x : Graph.getVertexSet()) { // checks all vertices
            System.out.println("vertex " + x + " parent: " + x.getParent()+ " " + Graph.getAdj(x.getName()));
            if(x.getParent() == null || !x.getName().equals(" ")){
                    x.setParent(x);
                    DFS_visit(x, Graph);
                }
            }
    }


    static void DFS_visit(vertex u, graph graph){ // DFS in lecture
        time++;
        u.setStart(time);
        for (vertex i : graph.getAdj(u.getName())) {
            if (i.getStart() == 0) {
                i.setParent(u);
                DFS_visit(u, graph);
            }
            if (i.getStart() != 0 && i.getEnd() == 0)
                System.out.println("Back edge found");
        }
        time++;
        u.setEnd(time);
        System.out.println("vertex " + u + " parent: " + u.getParent());
    }






    public static void main(String args[]) {
        graph G1 = new graph(7);

        G1 = G1.createGraphG1(7);

        printListG1(G1);
        DFS(G1);

    }
}
 */
