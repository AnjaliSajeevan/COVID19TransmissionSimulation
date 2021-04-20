/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

import Graph.Graph;
import Population.Population;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moumita
 */
public class PopulationPaintPanelTest {

    PopulationPaintPanel instance;

    public PopulationPaintPanelTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of paintComponent method, of class PopulationPaintPanel.
     */
    @Test(expected = NullPointerException.class)
    public void testPaintComponentThrowNullExceptionWhenTheMapsAreEmpty() {
        System.out.println("paintComponent");
        Graphics page = null;

        instance = new PopulationPaintPanel(new Population[10],
                new TreeMap<>(), new TreeMap<>(),
                false, new Rectangle(), new TreeMap<>(), 10.0, new Graph());

        instance.paintComponent(page);
    }

    /**
     * Test of getResultMap method, of class PopulationPaintPanel.
     */
    @Test(expected = NullPointerException.class)
    public void testGetResultMapThrowNullExceptionWhenTheMapsAreEmpty() {
        System.out.println("getResultMap");
        Map<Integer, Map<String, Integer>> expResult = null;
        instance = new PopulationPaintPanel(new Population[10],
                new TreeMap<>(), new TreeMap<>(),
                false, new Rectangle(), new TreeMap<>(), 10.0, new Graph());

        Map<Integer, Map<String, Integer>> result = instance.getResultMap();
        //assertEquals(expResult, result);
    }
    
}
