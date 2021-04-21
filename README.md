# Virus Transmission Simulation
The virus transmission simulation project is developed using Java and the simulation is implemented using Java swing graphical user interface (GUI), JFreeChart and Graphics 2D. 

![Virus Transmission Simulation Demo](demo/project.gif)

## Aim of the project

The aim of the project is to simulate a SARS-CoV-2 virus and analyze the effectiveness of various preventive measures. The following constraints/factors are taken into consideration for the simulation:

- The R-naught and k factor of the disease
- The usage and effectiveness of masks and social distancing
- The prevalence of testing and contact tracing
- The availability and efficacy of the vaccine
- Remote work and schools
- Quarantine
- Group event

The transmission of SARS-CoV-2 virus is also compared with SARS-CoV.

## Setup
 - first download and install jdk_8 here
 - setup the JAVA_HOME system variable
 - Clone or fork this repository.
```
  git clone https://github.com/AnjaliSajeevan/FinalProject_INFO6205.git
```

## Demo
[![Watch the demo](https://img.icons8.com/color/x/google-drive--v2.png)](https://drive.google.com/file/d/1Qg5RN25oT5t017FxmI4W8RkEqP1Ayza9/view?usp=sharing)

## Required Jar files 
- ini4j-0.5.1.jar
- jcommon-1.0.23.jar
- jfreechart-1.0.19.jar

## Programming Language 
- Java

## Development Tools
- Java SDK
- Netbeans IDE
- IntelliJ

## Simulation Panel
The Pane will show constraints(factors) , population counts for different health conditions, legends and assumptions along with the simulation of SAR-CoV and SAR-CoV-2 virus.

## Simulation Run
The simulation will begin with the click of START button in the panel. Constraints can be chosen from the available checkboxes in the left pane. Population slider is available to consider. R-naught value also is user defined.
This simulation can also be timed. We need give time in milliseconds along with population and constraints.
The simulation has START,STOP, RESUME and PAUSE buttons.
A graph can be plotted during the simulation to outline the growth of infection over the time interval (in days).

## Simulation Output
Population counts on different health conditions for the simulation can be viewed at the top pane. The panel shows real time infection rate graph right below the simulation.
The Plot button helps charting a graph which outlines the growth/decrease in the number of healthy, infected, hospitalized, or dead over the interval of days.
The log pane at the bottom will display the number of infected, healthy, recovered and dead people relative to time (in days) along with the different parameters/constraints selected.

## Configuration file
We use config.ini file to configure infection parameters, vaccine parameters, 2D Graphic component size and certain other conditions like hospital capacity. (More details are given in the config.ini file)
