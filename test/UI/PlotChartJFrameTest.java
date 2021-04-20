/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JPanel;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

/**
 *
 * @author moumita
 */
public class PlotChartJFrameTest {

    PlotChartJFrame instance;
    java.util.Map<Integer, java.util.Map<String, Integer>> map;

    public PlotChartJFrameTest() {
    }
    
    @Before
    public void setUp() {
        map = new TreeMap<>();
        java.util.Map<String, Integer> value = new TreeMap<>();
        value.put("Healthy", 100);
        value.put("Infected", 10);

        map.put(1, value);

        instance = new PlotChartJFrame(map, "0");
    }
    
    @After
    public void tearDown() {
        instance = null;
        map.clear();
    }

    /**
     * Test of drawChartPanel method, of class PlotChartJFrame.
     */
    @Test
    public void testDrawChartPanel() {
        // Arrange
        System.out.println("drawChartPanel");

        // Act
        JPanel result = instance.drawChartPanel();

        // Assert
        assertThat(result, instanceOf(javax.swing.JPanel.class));
    }

    /**
     * Test of getData method, of class PlotChartJFrame.
     */
    @Test
    public void testGetData() {
        // Arrange
        System.out.println("createDataset");

        XYSeriesCollection data = new XYSeriesCollection();
        XYSeries healthy = new XYSeries("Healthy");
        XYSeries infected = new XYSeries("Infected");

        map.forEach((t,m) -> {
            healthy.add(t, m.get("Healthy"));
            infected.add(t, m.get("Infected"));
        });

        data.addSeries(healthy);

        // Act
        XYDataset result = instance.createDataset();

        // Assert
        assertEquals(100, ((XYSeriesCollection)result).getY(0,0));
        assertEquals(10, ((XYSeriesCollection)result).getY(1,0));
    }
    
}
