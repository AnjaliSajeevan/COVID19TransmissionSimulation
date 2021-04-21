/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import Graph.Graph;
import Population.Population;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 *
 * @author moumita
 */
public class PopulationPaintPanelTest {

    PopulationPaintPanel instance;

    public PopulationPaintPanelTest() {
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
        // Arrange
        System.out.println("paintComponent");
        Graphics page = null;

        // Act
        instance = new PopulationPaintPanel(new Population[10],
                new TreeMap<>(), new TreeMap<>(),
                false, new Rectangle(), new TreeMap<>(), 10.0, new Graph());

        // Assert
        instance.paintComponent(page);
    }

    /**
     * Test of paintComponent method, of class PopulationPaintPanel.
     */
    @Test(expected = NullPointerException.class)
    public void testPaintComponent() {
        // Arrange
        System.out.println("paintComponent");
        Graphics page = null;

        Map<String, JLabel> labels = new HashMap();

        labels.put("population", new JLabel());
        labels.put("healthy", new JLabel());
        labels.put("severe", new JLabel());
        labels.put("recovered", new JLabel());
        labels.put("dead", new JLabel());
        labels.put("infected", new JLabel());
        labels.put("healthySAR", new JLabel());
        labels.put("severeSAR", new JLabel());
        labels.put("recoveredSAR", new JLabel());
        labels.put("deadSAR", new JLabel());
        labels.put("infectedSAR", new JLabel());

        Map<String, Boolean> factors = new HashMap();
        factors.put("quarantineCheck", false);
        factors.put("testingCheck", false);

        Map<String, Integer> parametersMap = new HashMap();
        parametersMap.put("populationBallHeight", 10);
        parametersMap.put("populationBallWidth", 5);
        parametersMap.put("asymptoticFraction", 5);

        instance = new PopulationPaintPanel(new Population[10],
                labels, factors,
                false, new Rectangle(), parametersMap, 10.0, new Graph());

        // Act
        instance.paintComponent(page);

        // Assert
        Assert.assertEquals(0, instance.getResultMap().size());
    }

    /**
     * Test of getResultMap method, of class PopulationPaintPanel.
     */
    @Test(expected = NullPointerException.class)
    public void testGetResultMapThrowNullExceptionWhenTheMapsAreEmpty() {
        // Arrange
        System.out.println("getResultMap");
        instance = new PopulationPaintPanel(new Population[10],
                new TreeMap<>(), new TreeMap<>(),
                false, new Rectangle(), new TreeMap<>(), 10.0, new Graph());

        // Act & Assert
        instance.getResultMap();
    }

    /**
     * Test of getResultMap method, of class PopulationPaintPanel.
     */
    @Test
    public void testGetResultMap() {
        // Arrange
        System.out.println("getResultMap");

        Map<String, JLabel> labels = new HashMap();

        labels.put("population", new JLabel());
        labels.put("healthy", new JLabel());
        labels.put("severe", new JLabel());
        labels.put("recovered", new JLabel());
        labels.put("dead", new JLabel());
        labels.put("infected", new JLabel());
        labels.put("healthySAR", new JLabel());
        labels.put("severeSAR", new JLabel());
        labels.put("recoveredSAR", new JLabel());
        labels.put("deadSAR", new JLabel());
        labels.put("infectedSAR", new JLabel());

        Map<String, Boolean> factors = new HashMap();
        factors.put("quarantineCheck", false);
        factors.put("testingCheck", false);

        Map<String, Integer> parametersMap = new HashMap();
        parametersMap.put("populationBallHeight", 10);
        parametersMap.put("populationBallWidth", 5);
        parametersMap.put("asymptoticFraction", 5);

        instance = new PopulationPaintPanel(new Population[10],
                labels, factors,
                false, new Rectangle(), parametersMap, 10.0, new Graph());

        // Act
        Map<Integer, Map<String, Integer>> map = instance.getResultMap();

        // Assert
        assertEquals(0, map.size());
    }
}
