/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author moumita
 */
public class PlotChartJFrame extends JFrame {
 
    static Map<Integer, Map<String, Integer>> resultsMap;
    String r0;
    
    public PlotChartJFrame(Map<Integer, Map<String, Integer>> resultsMap, String r0) {
        super("Covid-19 Simulator Plot");

        this.resultsMap = resultsMap;
        this.r0 = r0;

        JPanel chartPanel = drawChartPanel();
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(800, 700);
        setLocationRelativeTo(null);
    }
 
    public JPanel drawChartPanel() {
        String r = r0.equals("") ? "0" : r0;
        String chartTitle = "Covid-19 Simulator Plot With (Basic Reproduction Number) R-Naught: " + r;
        String xAxisLabel = "Days";
        String yAxisLabel = "Population";
 
        XYDataset dataset = createDataset();
 
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
            xAxisLabel, yAxisLabel, dataset);
        
        XYPlot plot = chart.getXYPlot();

        plot.setBackgroundPaint(Color.white);
 
        return new ChartPanel(chart);
    }
 
    public XYDataset createDataset() {
        XYSeriesCollection data = new XYSeriesCollection();
        XYSeries healthy = new XYSeries("Healthy");
        XYSeries infected = new XYSeries("Infected");
        XYSeries recovered = new XYSeries("Recovered");
        XYSeries hospitalized = new XYSeries("Hospitalized");
        XYSeries dead = new XYSeries("Dead");
        XYSeries asymptotic = new XYSeries("Asymptotic");

        resultsMap.forEach((t,m) -> {
            t /= 90;
            healthy.add(t, m.get("Healthy"));
            infected.add(t, m.get("Infected"));
            recovered.add(t, m.get("Recovered"));
            hospitalized.add(t, m.get("Recovered"));
            dead.add(t, m.get("Recovered"));
            asymptotic.add(t, m.get("Recovered"));
        }); 
 
        data.addSeries(healthy);
        data.addSeries(infected);
        data.addSeries(recovered);
        data.addSeries(hospitalized);
        data.addSeries(dead);
        data.addSeries(asymptotic);
 
        return data;
    }
}
