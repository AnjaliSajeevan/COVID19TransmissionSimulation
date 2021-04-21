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
 * This program uses JFree chart to plot the infection growth rate with respect to time.
 * @author moumita
 */
public class PlotChartJFrame extends JFrame {
 
    static Map<Integer, Map<String, Integer>> resultsMap;
    String r0;
    
    public PlotChartJFrame(Map<Integer, Map<String, Integer>> resultsMap, String r0) {
        super("Covid-19 Simulator Plot - Latest Run");

        this.resultsMap = resultsMap;
        this.r0 = r0;

        JPanel chartPanel = drawChartPanel();
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(800, 700);
        setLocationRelativeTo(null);
    }
 
    public JPanel drawChartPanel() {
        String r = r0.equals("") ? "0" : r0;
        String chartTitle = "Covid-19 Simulator Plot (Latest Run) With R-Naught: " + r;
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

        resultsMap.forEach((t,m) -> {
            t /= 90; // Factor the milliseconds to days.
            infected.add(t, m.get("Infected"));
            healthy.add(t, m.get("Healthy"));
            recovered.add(t, m.get("Recovered"));
            hospitalized.add(t, m.get("Hospitalized"));
            dead.add(t, m.get("Dead"));
        }); 
 
        data.addSeries(healthy);
        data.addSeries(infected);
        data.addSeries(recovered);
        data.addSeries(hospitalized);
        data.addSeries(dead);
 
        return data;
    }
}
