/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.HashSet;

/**
 *
 * @author Anjali
 */
public class Graph {
    private HashSet<Vertex> nodes;
    
    public Graph(){
        nodes = new HashSet<>();
    }
    

    public boolean AddVertex(Vertex v){
        return nodes.add(v);
    }
    
    
    public boolean AddEdge(String v1, String v2){
        boolean result = false;
        Vertex a1=null;
        Vertex a2=null;
        a1=getVertex(v1);
        a2=getVertex(v2);
        result = a1.getEdges().add(new Edge(a2));
        return result;
    }
    
    public boolean checkVertex(String x) {
        
        boolean result = false;
        for (Vertex s : nodes) {

            if (x.equalsIgnoreCase(s.getName())) {
                return true;
            } else {
                result = false;
            }
        }
        return result;
    }
    
     public Vertex getVertex(String x){
        Vertex result = null;
        for (Vertex s : nodes) {
            if(x.equalsIgnoreCase(s.getName())){
                result = s;
            }
        }
        return result;
    }

    public void printGraph(){
        System.out.println("---------------------Contact Tracing------------------------------");
        for(Vertex v : nodes){
            System.out.print("Person: "+ v.getName() + " -> ");
            for(Edge e : v.getEdges()){
                System.out.print("infected: " + e.getDestVertex().getName() + " | ");
            }
            System.out.print("\n");
        }
    }
}

