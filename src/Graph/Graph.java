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
    
    
    public boolean AddEdge(Vertex v1, Vertex v2){
        boolean result = false;
       boolean flag=false;
              for(Edge e : v1.getEdges()){
               if(e.getDestVertex().getName().equals(v2.getName())){
//                     System.out.println("Inside add edge true");
               result=false;
               flag=true;
            }
               
    }      if(flag==false){
                   result = v1.getEdges().add(new Edge(v2));
               }
//    System.out.println("Add edge flag:"+flag);
//              System.out.println("Add edge:"+result);
              return result;
    }
    
    public boolean checkVertex(String x) {
//        System.out.println("Hello");
        boolean result = false;
        for (Vertex s : nodes) {
//        System.out.println("Hi");
            if (x.equalsIgnoreCase(s.getName())) {
//            System.out.println("Inside string s "+x+"vertex:"+s.getName());
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
        //I printed it like this. You can print it however you want though
        for(Vertex v : nodes){
            System.out.print("Person: "+ v.getName() + ": ");
            for(Edge e : v.getEdges()){
                System.out.print("infected: " + e.getDestVertex().getName() + " | ");
            }
            System.out.print("\n");
        }
    }
}

