/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Population;

import UI.Dashboard;
import java.util.Map;

/**
 *
 * @author Manasa
 */
public class Population {
    

    private double x, y, xTemp, yTemp;
    private int width= 700, height = 400;
    private int status; // 0 for healthy, 1 for infected, 2 for hospitalized , 3 for recovered, 4 for dead
    private int countInfected, hospitalCapacity,countHospitalized,vaccineEffective;
    private boolean firstInfected,quarantined,distancing,vaccinated,prone, ignore, comorbidity,compareVirus;
    private double risk;
    private long infectTime;


    public Population(int x, int y,Map<String,Boolean> conditions ,int hospitalCapacity) {
        this.x = x;
        this.y = y;
        this.compareVirus = conditions.get("compareVirus"); //Sar Virus(Type2 virus)
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
        this.vaccinated = conditions.get("vaccinated");
        if(vaccinated){
            status = 5;
        }
    }

    public void countStatus() {
        if ((status != 2 && status != 4) && (!quarantined)) {
            if (!compareVirus) {
                if (x + xTemp + 10 > width || x + xTemp < 0) {
                    xTemp = -xTemp;
                }
                if (y + yTemp + 10 > height || y + yTemp < 0) {
                    yTemp = -yTemp;
                }
            } else {
                if (x + xTemp + 10 > width * 2 || x + xTemp < Dashboard.WIDTH) {
                    xTemp = -xTemp;
                }
                if (y + yTemp + 10 > height || y + yTemp < 0) {
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
        if (!ignore) {
            if (Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2)) < 10) {
                if(quarantineCheck){
                if (p.getStatus() == 1 && Math.random() < 0.85 && (status == 0 || status==3 || status ==5) && (this.quarantined==false)) {
                    status = 1;
                    this.setInfectTime();
                    if(infectedQuarantineNum > 0){
                    this.quarantined=true;
                    infectedQuarantineNum--;
                    }
                }
                }else{
                if (p.getStatus() == 1 && Math.random() < 0.85 && (status == 0 || status==3 || status==5) ) {
                    status = 1;
                    this.setInfectTime();
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
    
}
