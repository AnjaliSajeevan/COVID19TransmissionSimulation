/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import Population.Population;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    String populationMessage="";
    JLabel populationLabel;
    public PopulationPaintPanel(Population[] people, JLabel populationLabel){
        this.people=people;
        this.populationLabel=populationLabel;
    }
    public void paintComponent(Graphics page) {
        
  super.paintComponents(page);
        // Draw people (normal)
       healthy = 0;
       infected=0;
       hospitalized=0;
       recovered=0;
       dead=0;
       for (int i = 0; i < people.length; i++) {
            switch (people[i].getStatus()) {
                case 0:
                    page.setColor(Color.green);
                    healthy++;
                    break;
                case 1:
                    page.setColor(Color.red);
                    infected++;
                    break;
                case 2:
                    page.setColor(Color.pink);
                    hospitalized++;
                    break;
                case 3:
                    page.setColor(Color.blue);
                    recovered++;
                    break;
                case 4:
                    page.setColor(Color.gray);
                    dead++;
                    break;
            }
          
            page.fillOval((int) people[i].getX(), (int) people[i].getY(), 10, 10);
            for (int j = 0; j < people.length; j++) {
                if (i != j) {
                    people[i].check(people[j]);
                }
            }


        }
        populationMessage="Healthy: "+healthy;
        populationMessage+="\nInfected: "+infected;
        populationMessage+="\nHospitalized: "+hospitalized;
        populationMessage+="\nRecovered: "+recovered;
        populationMessage+="\nDead: "+dead;
        populationLabel.setText("Population -"+populationMessage);
      
    }
    
}
