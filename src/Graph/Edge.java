/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author Anjali
 */
public class Edge {
    private Vertex destVertex;

    public Edge(Vertex dest){
        this.destVertex = dest;

    }

    public Vertex getDestVertex(){
        return destVertex;
    }
}

