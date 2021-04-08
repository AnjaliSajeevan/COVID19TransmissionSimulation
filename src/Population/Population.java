/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Population;

import UI.Dashboard;

/**
 *
 * @author Manasa
 */
public class Population {
    

    private double x, y, xTemp, yTemp;
    private int width= 700, height = 400;
    private int status; // 0 for healthy, 1 for infected, 2 for hospitalized , 3 for recovered, 4 for dead
    private int countInfected, countHospitalized;
    private boolean firstInfected, ignore, compareVirus;
    private double risk;

    public Population(int x, int y, boolean infected, boolean compareVirus) {
        this.x = x;
        this.y = y;
        this.compareVirus = compareVirus; //Sar Virus(Type2 virus)
        risk = Math.random();
        xTemp = Math.random() * 4.0 - 2.0;
        yTemp = Math.sqrt(4 - Math.pow(xTemp, 2)) * (Math.random() < 0.5 ? -1.0 : 1.0);
        countInfected = 0;
        countHospitalized = 0;
        status = (infected ? 1 : 0);
        firstInfected = infected;
    }

    public void countStatus() {
        if (status != 2 && status != 4) {
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
        if (!firstInfected) {
            if (status == 1) {//infected
                countInfected++;
                if (countInfected > 800) {
                    status = 3;
                } else if (risk < 0.02) {
                    status = 4;
                }
            } else if (status == 2) {//hospitalized
                countHospitalized++;
                if (countHospitalized > 500 && risk < 0.5) {//dead
                    status = 4;
                } else if (countHospitalized > 500) {//recovered
                    status = 3;
                }
            }
        }
    }

    public void check(Population p) {
        if (!ignore) {
            if (Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2)) < 10) {
                if (p.getStatus() == 1 && Math.random() < 0.85 && status == 0) {
                    status = 1;
                }
                if (status != 2) {
                    xTemp = (Math.random() * 2 * (x - p.getX() < 0 ? -1.0 : 1.0));
                    yTemp = (Math.sqrt(4 - Math.pow(xTemp, 2))) * (y - p.getY() < 0 ? -1.0 : 1.0);
                    p.ignoreCheck();
                }
                if (firstInfected) {
                    firstInfected = false;
                }
            }
        } else {
            ignore = false;
        }
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
}
