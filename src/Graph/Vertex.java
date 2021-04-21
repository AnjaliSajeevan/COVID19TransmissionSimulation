/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.LinkedList;

/**
 *
 * @author Anjali
 */
public class Vertex {
   
    private String name;
    private LinkedList<Edge> edgeList;

    public Vertex(String name){
        this.name = name;
        edgeList = new LinkedList<>();
    }

    public String getName(){
        return name;
    }

    public LinkedList<Edge> getEdges(){
        return edgeList;
    }
}

