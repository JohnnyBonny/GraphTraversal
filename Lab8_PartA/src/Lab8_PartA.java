/* I did not create a node class for the graph, so I had to represent the vertex as an ArrayList
of Integers. Knowing that I was provided a graph in the instructions, I generated the same graph in
my CreateGraph function. One tricky part that came from my project was saving the BFS order. I
solved this by storing the values into a dummy ArrayList. Lastly, the result of me not creating a node
class for the node class was that I had to build a hashmap that would mark the nodes that were a parent node */

import java.util.*;
// changed  code
class graph {
    ArrayList<ArrayList<Integer>> graph;
    int nodeNr; // represents the number of nodes that are in the graph
    Map<String,Integer> weight = new HashMap<>();

    graph(int num){
        nodeNr = num;
        graph = new ArrayList<>();

        for(int i = 0; i < nodeNr;i++){
            graph.add(new ArrayList<>());
        }
    }

    void addEdge(int edge1,int edge2,int value){
        graph.get(edge1).add(edge2);
        weight.putIfAbsent(String.valueOf(edge1)+edge2,value);
    }

    void printList(){
        for(int i= 0; i < nodeNr ; i++){
            System.out.print(i + " -> ");
            for(int x: graph.get(i)){
                System.out.print(x + ", ");
            }
            System.out.println();
        }
    }
/*
    graph createGraphG1(int order){ //creates the same graph as the one in the example
        graph mainGraph = new graph(order);
        mainGraph.addEdge(0,1); //a
        mainGraph.addEdge(0,2);
        mainGraph.addEdge(0,3);
        mainGraph.addEdge(1,3);//b
        mainGraph.addEdge(2,3);//c
        mainGraph.addEdge(3,4);//d
        mainGraph.addEdge(4,6);//e
        mainGraph.addEdge(5,4);//f

        return mainGraph;
    }

 */

    graph createGraphG2(int order){ //creates the same graph as the one in the example
        graph mainGraph = new graph(order);
        mainGraph.addEdge(0,1,3); //a
        mainGraph.addEdge(0,2,4);
        mainGraph.addEdge(1,2,1);//b
        mainGraph.addEdge(1,3,4);//b
        mainGraph.addEdge(1,4,1);//b
        mainGraph.addEdge(2,4,2);//c
        mainGraph.addEdge(3,5,7);//d
        mainGraph.addEdge(4,2,8);//e
        mainGraph.addEdge(4,3,2);//e
        mainGraph.addEdge(5,4,6);//f

        return mainGraph;
    }

    ArrayList<Integer> adjacentV(Integer vector){
        ArrayList<Integer> dummy = new ArrayList<>();
        for(int x: graph.get(vector)){
            dummy.add(x);
        }
        return dummy;
    }

    ArrayList<Integer> getVertex() {
        ArrayList<Integer> dummy  = new ArrayList<>();
        for(int i = 0; i<nodeNr ; i++){
            dummy.add(i);
        }
        return dummy;
    }

    public Map<String, Integer> getWeight() {
        return weight;
    }
}

public class Lab8_PartA{
    public static int time;
    public static Map<Integer ,Integer> parentMap = new HashMap<>();
    public static Map<Integer ,Integer> startMap = new HashMap<>();
    public static Map<Integer ,Integer> endMap = new HashMap<>();
    public static LinkedList<Integer> vertex = new LinkedList<>();

    static void DFS(graph graph){
        for(Integer v : graph.getVertex()){
            if(!parentMap.containsKey(v)){
                parentMap.put(v,v);
                DFS_visit(v,graph);
            }
        }
        System.out.println("Topologiocal order (From Right to Left): " + vertex);
        for(Integer v : graph.getVertex()){
            System.out.printf("%d: start: %d | end: %d | parent: %d\n",v, startMap.get(v), endMap.get(v), parentMap.get(v));
        }
    }

    static void DFS_visit(int u, graph graph){
        time++;
        startMap.put(u,time);
        for(Integer i: graph.adjacentV(u)){
            if(!startMap.containsKey(i)){
                parentMap.put(i,u);
                DFS_visit(i,graph);
            }
            if(startMap.containsKey(i) && !endMap.containsKey(i)){
                System.out.println("Back edge found. Ignore topological order!!!");
            }
        }
        time++;
        endMap.put(u,time);
        vertex.push(u);

    }



    public static void main(String[] args) {
        graph g1 = new graph(6);
        g1 = g1.createGraphG2(6);
        System.out.print(g1.getWeight());
        /*
        g1.printList();
        DFS(g1);

        System.out.println("");
        time = 0;
        parentMap.clear();
        startMap.clear();
        endMap.clear();
        vertex.clear();

        graph g2 = new graph(6);
        g2 = g2.createGraphG2(6);
        g2.printList();
        DFS(g2);
        */


    }


}
