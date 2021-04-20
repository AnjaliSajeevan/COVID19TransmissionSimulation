/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import Graph.Graph;
import Population.Population;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This program uses 2D Graphics to paint the population components based on their status(health condition)
 * @author Manasa
 */
public class PopulationPaintPanel extends JPanel{
    
    private Population[] people;
    private JLabel populationLabel, labelHealthy,labelSevere, labelRecovered, labelDead, labelInfected;
    private JLabel labelSARHealthy,labelSARSevere, labelSARRecovered, labelSARDead, labelSARInfected;
    private Rectangle groupBox;
    private Graph graph;
    private ArrayList<GraphPlot> points =new ArrayList<GraphPlot>();
    private Map<Integer, Map<String, Integer>> resultMap;
    private String populationMessage="";
    private boolean quarantineCheck,testingCheck,groupEvent;
    private int healthy, infected, hospitalized,recovered, dead, infectedQuarantineNum,mark, ballH, ballW,
           asymptoticPeople,asymptoticFraction, time=0;
    private double infectedQuarantinePercentage;

            
    public PopulationPaintPanel(Population[] people, Map<String,JLabel> labels,
        Map<String,Boolean> factors,boolean groupEvent,
        Rectangle groupBox,Map<String,Integer> parametersMap,
        double infectedQuarantinePercentage,Graph graph){

        resultMap = new TreeMap<>();

        this.people=people;
        this.populationLabel=labels.get("population");
        this.labelHealthy=labels.get("healthy");
        this.labelSevere=labels.get("severe");
        this.labelRecovered=labels.get("recovered");
        this.labelDead=labels.get("dead");
        this.labelInfected = labels.get("infected");
        this.labelSARHealthy=labels.get("healthySAR");
        this.labelSARSevere=labels.get("severeSAR");
        this.labelSARRecovered=labels.get("recoveredSAR");
        this.labelSARDead=labels.get("deadSAR");
        this.labelSARInfected = labels.get("infectedSAR");
        this.quarantineCheck = factors.get("quarantineCheck");
        this.testingCheck = factors.get("testingCheck");
        this.groupBox=groupBox;
        this.groupEvent=groupEvent;
        this.ballH = parametersMap.get("populationBallHeight");
        this.ballW = parametersMap.get("populationBallWidth");
        this.asymptoticFraction = parametersMap.get("asymptoticFraction");
        this.infectedQuarantinePercentage = infectedQuarantinePercentage;
        this.graph=graph;

    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponents(g);
        time+=15;
        healthy = 0;
        infected=0;
        hospitalized=0;
        recovered=0;
        dead=0;
        asymptoticPeople=0;

        boolean compareSAR =people[0].isCompareVirus();
        for (int i = 0; i < people.length; i++) {
            Map<String, Integer> map = new HashMap<>();

            switch (people[i].getStatus()) {
                case 0:                                  //Healthy condition
                    g.setColor(Color.green);
                    healthy++;
                    break;
                case 1:                                //Infected condition
                    if(!testingCheck){       //If Testing is checked, all infected person would be prominent.
                        asymptoticPeople++;
                        if((asymptoticPeople%asymptoticFraction)==0){  // Every 3rd person is asymptotic
                            g.setColor(new Color(255,192,203));
                        }else{
                            g.setColor(Color.red);
                        }
                    }else{
                       g.setColor(Color.red);
                    }
                    infected++;
                    break;
                case 2:                                 //Severe or Hospitalized
                    g.setColor(new Color(146,0,10));
                    hospitalized++;
                    break;
                case 3:                               //Recovered
                    g.setColor(Color.green);
                    recovered++;
                    break;
                case 4:                              //Dead
                    g.setColor(Color.gray);
                    dead++;
                    break;
                case 5:                              //Vaccinated person
                    g.setColor(new Color(41,171,135));
                    healthy++;

                    break;
            }

            g.fillOval((int) people[i].getX(), (int) people[i].getY(), ballH, ballW);

            infectedQuarantineNum = (int)(infectedQuarantinePercentage * infected);
            for (int j = 0; j < people.length; j++) {
                if (i != j) {
                    if(people[j].isQuarantined()){
                        people[i].ignoreCheck();
                    }else{
                        infectedQuarantineNum = people[i].check(people[j],infectedQuarantineNum,quarantineCheck,graph);
                    }
                }
            }
       }

        Map<String, Integer> map = new HashMap<>();
        map.put("Infected", infected);
        map.put("Healthy", healthy);
        map.put("Hospitalized", hospitalized);
        map.put("Recovered", recovered);
        map.put("Dead", dead);
        map.put("Asymptomatic", asymptoticPeople);

        if(time % 90 == 0) // 15 is divisible by 90.
            resultMap.put(time, map);

        points.add(new GraphPlot(time/80,infected));

        if(!compareSAR){
            labelHealthy.setText(String.valueOf(healthy));
            labelDead.setText(String.valueOf(dead));
            labelRecovered.setText(String.valueOf(recovered));
            labelInfected.setText(String.valueOf(infected));
            labelSevere.setText(String.valueOf(hospitalized));

        }else{
            labelSARHealthy.setText(String.valueOf(healthy));
            labelSARDead.setText(String.valueOf(dead));
            labelSARRecovered.setText(String.valueOf(recovered));
            labelSARInfected.setText(String.valueOf(infected));
            labelSARSevere.setText(String.valueOf(hospitalized));
        }

        //real- time graph plot
        for(GraphPlot p:points){
            g.setColor(Color.RED);
            g.fillOval(p.time,625-p.infectedNumbers/3,5,5);
        }
      

        //groupEvent
        if (groupEvent == true) {
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.BLACK);
            g2.draw(groupBox);
        }
    }

    public Map<Integer, Map<String, Integer>> getResultMap(){
        return resultMap;
    }
}
