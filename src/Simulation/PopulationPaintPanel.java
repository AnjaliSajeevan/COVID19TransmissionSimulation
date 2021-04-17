/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import Population.Population;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;


/**
 *
 * @author Manasa
 */
public class PopulationPaintPanel extends JPanel{
    
    Population[] people;
    int healthy;
    int infected;
    int hospitalized;
    int recovered;
    int dead;
    int asymptoticPeople;
    int mark;
    String populationMessage="";
    JLabel populationLabel, labelHealthy,labelSevere, labelRecovered, labelDead, labelInfected;
    JLabel labelSARHealthy,labelSARSevere, labelSARRecovered, labelSARDead, labelSARInfected;
    int infectedQuarantineNum;//infectedQuarantineNum - No of infected people that qurantine when qurantine is checked .
    boolean quarantineCheck,testingCheck;
    Rectangle groupBox;boolean groupEvent;
    int populationNum;
            
    public PopulationPaintPanel(Population[] people, Map<String,JLabel> labels, Map<String,Boolean> factors,boolean groupEvent,Rectangle groupBox,int populationNum){
        
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
        this.infectedQuarantineNum = (int)(0.2 * people.length);
        this.quarantineCheck = factors.get("quarantineCheck");
        this.testingCheck = factors.get("testingCheck");
        this.groupBox=groupBox;
        this.groupEvent=groupEvent;
        this.populationNum=populationNum;
    }
    public void paintComponent(Graphics page) {
        
  super.paintComponents(page);
        // Draw people (normal)
       healthy = 0;
       infected=0;
       hospitalized=0;
       recovered=0;
       dead=0;
       asymptoticPeople=0;
       boolean compareSAR =people[0].isCompareVirus();
       for (int i = 0; i < people.length; i++) {
           
            switch (people[i].getStatus()) {
                case 0:
                    page.setColor(Color.green);
                    
                    healthy++;
                    break;
                case 1:
                    if(!testingCheck){       //If Testing is checked, all infected person would be prominent.
                        asymptoticPeople++;
                        if((asymptoticPeople%3)==0){  // Every 3rd person is asymptotic
                            page.setColor(Color.orange);  
                        }else{
                            page.setColor(new Color(255,135,141));
                        }
                    }else{
                       page.setColor(Color.pink); 
                    }
                    infected++;
                    break;
                case 2:
                    page.setColor(Color.red);                  
                    hospitalized++;
                    break;
                case 3:
                    page.setColor(Color.green);
                    recovered++;
                    break;
                case 4:
                    page.setColor(Color.gray);
                    dead++;
                    break;
                case 5:
                    page.setColor(new Color(41,171,135));
                    healthy++;
                   
                    break;
            }
   
            page.fillOval((int) people[i].getX(), (int) people[i].getY(), 10, 10);
            
            infectedQuarantineNum = (int)(0.2 * infected);
            for (int j = 0; j < people.length; j++) {
                if (i != j) {
                    if(people[j].isQuarantined()){
                        people[i].ignoreCheck();
                    }else{
                    infectedQuarantineNum = people[i].check(people[j],infectedQuarantineNum,quarantineCheck);
                    }
                }
            }
       }
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
        
        populationLabel.setText("Population Count:"+people.length);
      
        if (groupEvent == true) {
            Graphics2D g2 = (Graphics2D) page;
            page.setColor(Color.BLACK);
            g2.draw(groupBox);
            
            
//graph
        
        }
    }
    
}
