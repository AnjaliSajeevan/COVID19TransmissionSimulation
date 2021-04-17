/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Population;

import Graph.Graph;
import Graph.Vertex;
import UI.Dashboard;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Manasa
 */
public class Population {
    

    private double x, y, xTemp, yTemp;
    Rectangle mainPanel = new Rectangle(0, 0, 700, 400);
    private int status; // 0 for healthy, 1 for infected, 2 for hospitalized , 3 for recovered, 4 for dead
    private int countInfected, hospitalCapacity,countHospitalized,vaccineEffective;
    private boolean firstInfected,quarantined,distancing,vaccinated,prone, ignore, comorbidity,compareVirus;
    private double risk;
    private long infectTime;
    private double x_f;
    private double y_f;
    private int populationNum;
    boolean groupEvent;
    Rectangle groupBox;
    private String code;
    private int Collisioncount=0;
    Map<String,Integer> map= new HashMap<String,Integer>();
    Graph graph;


    public Population(int x, int y,Map<String,Boolean> conditions ,int hospitalCapacity,boolean groupEvent,Rectangle groupBox,int populationNum,Graph graph,boolean compareVirus) {
        this.x = x;
        this.y = y;
        this.groupEvent=groupEvent;
        this.groupBox=groupBox;
        this.graph=graph;
        this.compareVirus = compareVirus; //Sar Virus(Type2 virus)
        this.comorbidity = conditions.get("comorbidity");
        this.hospitalCapacity = hospitalCapacity;
        this.prone = conditions.get("prone");
        risk = Math.random();
        this.distancing = conditions.get("distancingCheck");
        if(distancing){     //Displacement is less to keep distance from others.
        xTemp = 0.4;
        yTemp = Math.sqrt(4 - Math.pow(xTemp, 2)) * (Math.random() < 0.5 ? -0.4 : 0.4);
        }else{          //Usually displacement between 0.4 to 1.9
        xTemp = Math.random() * 4.0 - 2.0;
        yTemp = Math.sqrt(4 - Math.pow(xTemp, 2)) * (Math.random() < 0.5 ? -1.0 : 1.0);
        }
        countInfected = 0;
        countHospitalized = 0;
        status = (conditions.get("infected") ? 1 : 0);
        firstInfected = conditions.get("infected");
        this.quarantined = conditions.get("quarantine");
        if(compareVirus){
            vaccinated = false;
        }else{             
        this.vaccinated = conditions.get("vaccinated");
        }
        if(vaccinated){
            status = 5;
        }
        uniqueCode();
        this.populationNum=populationNum;
        this.graph=graph;
       
        
    }
    
    public Map getCodeDash() {
        return map;
    }

    public String uniqueCode() {
        List<Integer> numbers = new ArrayList();
        for(int i = 0; i < 10; i++){
        numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for (int i = 0; i < 6; i++) {
            result += numbers.get(i).toString();
            code = 'A' + result;
    }
        return code;
    }
    

    public void countStatus() {
        x_f = x + xTemp;
        y_f = y + yTemp;
        if(status == 4 ){
            return;
        }
        if ((status != 2 && status != 4) && (!quarantined)) {
                if (groupEvent == true) {
                   
                    if (groupBox.contains(x, y) == true) {
                        if (groupBox.contains(x_f, y_f) == false) {

                            if (x_f < groupBox.x || x_f > groupBox.width) {
                                xTemp = -xTemp;
                            }
                            if (y_f < groupBox.y || y_f > groupBox.height) {
                                yTemp = -yTemp;
                            }
                        }
                    } else {
                        if (x + xTemp > mainPanel.width || x + xTemp < 0) {
                            xTemp = -xTemp;
                        }
                        if (y + yTemp > mainPanel.height || y + yTemp < 0) {
                            yTemp = -yTemp;
                        }
                        }
                    }



                if (groupEvent == false) {

                    if (x + xTemp + 10 > mainPanel.width || x + xTemp < 0) {
                        xTemp = -xTemp;
                    }
                    if (y + yTemp + 10 > mainPanel.height || y + yTemp < 0) {
                        yTemp = -yTemp;
                    }

                }

            x += xTemp;
            y += yTemp;
        }
       double difference = ((System.currentTimeMillis()) - (this.getInfectTime())) / 1000F;
        if (!firstInfected) {
            if (status == 1) {//infected     
                if(comorbidity){
                    if(countHospitalized < hospitalCapacity ) {
                        status = 2;
                        countHospitalized++;
                        this.quarantined = true;
                    }else{
                       status = 2;
                    }
                }else{
                       if(difference >= 5 ){
                           status = 3;
                       }                  
                }

            } else if (status == 2) {//hospitalized
                double infectTime = ((System.currentTimeMillis()) - (this.getInfectTime())) / 1000F;
                if(infectTime >= 3 ){
                    if (comorbidity){//dead
                        status = 4;
                    }
                }
            } else if(infectTime >= 5 ){ {//recovered
                    status = 3;
                    quarantined=false;
                }
            }
        }
    }

    public int check(Population p, int infectedQuarantineNum, boolean quarantineCheck){
        Vertex v1 = null;
        Vertex v2 = null;
        if (!ignore) {
            if (Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2)) < 10) {
                if (graph.checkVertex(p.code) == false) {
                    v1 = new Vertex(p.code);
                    graph.AddVertex(v1);
                } else  {

                    v1 = graph.getVertex(p.code);
                }
                
//                System.out.println(" Name of V1: "+v1.getName());
                
                if (graph.checkVertex(code) == false) {
                    v2 = new Vertex(code);
                    graph.AddVertex(v2);
                } else {

                    v2 = graph.getVertex(code);
                }
                
//                System.out.println(" Name of V2: "+v2.getName());
                graph.AddEdge(v1, v2);
                
                if(quarantineCheck){
                if (p.getStatus() == 1 && Math.random() < 0.85 && (status == 0 || status==3 || status ==5) && (this.quarantined==false)) {
                    if(compareVirus){
                               double infectTime = ((System.currentTimeMillis()) - (p.getInfectTime())) / 1000F;
                               if(infectTime >=3){
                                        status = 1;
                                        this.setInfectTime();
                                            if(infectedQuarantineNum > 0){
                                            this.quarantined=true;
                                            infectedQuarantineNum--;
                                        }                                  
                               }
                    }else{
                        status = 1;
                        this.setInfectTime();
                        if(infectedQuarantineNum > 0){
                            this.quarantined=true;
                            infectedQuarantineNum--;
                        }
                    }
                }
                }else{
                if (p.getStatus() == 1 && Math.random() < 0.85 && (status == 0 || status==3 || status==5) ) {
                    if(compareVirus){
                        double infectTime = ((System.currentTimeMillis()) - (p.getInfectTime())) / 1000F;
                          if(infectTime >=3){
                          status = 1;
                          this.setInfectTime();                              
                          }
                    }else{
                    status = 1;
                    this.setInfectTime();
                    }                    
                }
                }
                if ((!quarantined) &&  (status != 2) ) {
                    if(distancing){     //Displacement is less to keep distance from others.
                        xTemp = (Math.random() * 2 * (x - p.getX() < 0 ? -0.4 : 0.4));
                        yTemp = (Math.sqrt(4 - Math.pow(xTemp, 2))) * (y - p.getY() < 0 ? -0.4 : 0.4);
                    }else{
                        xTemp = (Math.random() * 2 * (x - p.getX() < 0 ? -1.0 : 1.0));
                        yTemp = (Math.sqrt(4 - Math.pow(xTemp, 2))) * (y - p.getY() < 0 ? -1.0 : 1.0);
                        p.ignoreCheck();
                    }
                }
                if((vaccinated) && (!prone)){
                    p.ignoreCheck();
                    
                }
                if (firstInfected) {
                    firstInfected = false;
                }
            }
//            graph.printGraph();
//        Collisioncount+=1;    
//        map.put(code, Collisioncount);
       
        } else {
            if(!((vaccinated) && (!prone))){
            ignore = false;
            }
        }
        return infectedQuarantineNum;  
    }

    public void ignoreCheck() {
        ignore = true;
    }

    public void hospitalize() {
        if (!firstInfected) {
            status = 2;
        }
    }

    public void kill() {
        status = 4;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isInfected() {
        return (status == 1 ? true : false);
    }

    public boolean isQuarantined() {
        return quarantined;
    }

    public void setQuarantined(boolean quarantined) {
        this.quarantined = quarantined;
    }
    

    public long getInfectTime() {
        return this.infectTime;
    }

    public void setInfectTime() {
        this.infectTime = System.currentTimeMillis();
    }

    // For unit testing.
    public void setInfectTime(long time){
        this.infectTime = time;
    }

    public boolean getIgnore(){
        return ignore;
    }

    public void setIgnore(boolean ignore){
        this.ignore = ignore;
    }

    public void setFirstInfected(boolean firstInfected){
        this.firstInfected = firstInfected;
    }

    public int getCountHospitalized(){
        return countHospitalized;
    }

    public void setHospitalCapacity(int hospitalCapacity) {
        this.hospitalCapacity = hospitalCapacity;
    }

    public void setComorbidity(boolean comorbidity){
        this.comorbidity = comorbidity;
    }

    public boolean isCompareVirus() {
        return compareVirus;
    }
    
}
