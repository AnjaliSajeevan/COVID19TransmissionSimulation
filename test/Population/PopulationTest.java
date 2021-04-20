/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Population;

import Graph.Graph;
import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 *
 * @author moumita
 */
public class PopulationTest {
    private Population instance;
    private Map<String,Boolean> conditions;

    public PopulationTest(){
        conditions = new HashMap<>();
        conditions.put("compareVirus", true);
        conditions.put("comorbidity", true);
        conditions.put("prone", true);
        conditions.put("distancingCheck", true);
        conditions.put("infected", true);
        conditions.put("quarantine", true);
        conditions.put("vaccinated", true);
    }

    @Before
    public void setUp() {
        instance = new Population(100, 100, conditions, 500, 
                false, null, 1000, false, 0);
    }
    
    @After
    public void tearDown() {
        instance = null;
        conditions = null;
    }

    /**
     * Test of check method, of class Population.
     */
    @Test
    public void testCheck() {
        // Arrange - ToDo.
        System.out.println("check");
        int infectedQuarantineNum = 0;
        boolean quarantineCheck = false;
        int expResult = 0;

        // Act
        int result = instance.check(instance, infectedQuarantineNum, quarantineCheck, new Graph());

        // Assert
        assertEquals(expResult, result);
    }

    /**
     * Test of ignoreCheck method, of class Population.
     * It sets the ignore flag to true.
     */
    @Test
    public void testIgnoreCheck() {
        // Arrange
        System.out.println("ignoreCheck");
        instance.setIgnore(false);

        // Act
        instance.ignoreCheck();

        // Assert
        Assert.assertTrue(instance.getIgnore());
    }

    /**
     * Test of hospitalize method, of class Population.
     * This method checks if the firstInfected is false and then sets the status to 2.
     */
    @Test
    public void testHospitalize() {
        // Arrange
        System.out.println("hospitalize");
        instance.setStatus(0);
        instance.setFirstInfected(false);

        // Act
        instance.hospitalize();

        // Assert
        Assert.assertEquals(instance.getStatus(), 2);
    }

    /**
     * Test of kill method, of class Population.
     */
    @Test
    public void testKill() {
        // Arrange
        System.out.println("kill");
        int expectedStatus = 4;

        // Act
        instance.kill();

        // Assert
        assertEquals(expectedStatus, instance.getStatus());
    }

    /**
     * Test of getStatus method, of class Population.
     */
    @Test
    public void testGetStatus() {
        // Arrange
        System.out.println("getStatus");
        int expResult = 1;
        instance.setStatus(expResult);

        // Act
        int result = instance.getStatus();

        // Assert
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Population.
     */
    @Test
    public void testSetStatus() {
        // Arrange
        System.out.println("getStatus");
        int expResult = 1;

        // Act
        instance.setStatus(expResult);

        // Assert
        assertEquals(expResult, instance.getStatus());
    }

    /**
     * Test of getX method, of class Population.
     */
    @Test
    public void testGetX() {
        // Arrange
        System.out.println("getX");
        double expResult = 100.0;

        // Act
        double result = instance.getX();

        // Assert
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setX method, of class Population.
     */
    @Test
    public void testSetX() {
        // Arrange
        System.out.println("setX");
        double x = 200.0;

        // Act
        instance.setX(x);

        // Assert
        assertEquals(x, instance.getX(), 0.0);
    }

    /**
     * Test of getY method, of class Population.
     */
    @Test
    public void testGetY() {
        // Arrange
        System.out.println("getY");
        double expResult = 100.0;

        // Act
        double result = instance.getY();

        // Assert
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setY method, of class Population.
     */
    @Test
    public void testSetY() {
        // Arrange
        System.out.println("setY");
        double y = 200.0;

        // Act
        instance.setY(y);

        // Assert
        assertEquals(y, instance.getY(), 0.0);
    }

    /**
     * Test of isInfected method, of class Population.
     */
    @Test
    public void testIsInfected() {
        // Arrange
        System.out.println("isInfected");
        boolean expResult = false;

        // Act
        boolean result = instance.isInfected();

        // Assert
        assertEquals(expResult, result);
    }

    /**
     * Test of isQuarantined method, of class Population.
     */
    @Test
    public void testIsQuarantined() {
        // Arrange
        System.out.println("isQuarantined");
        boolean expResult = false;
        instance.setQuarantined(expResult);

        // Act
        boolean result = instance.isQuarantined();

        // Assert
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuarantined method, of class Population.
     */
    @Test
    public void testSetQuarantined() {
        // Arrange
        System.out.println("setQuarantined");
        boolean quarantined = false;

        // Act
        instance.setQuarantined(quarantined);

        // Assert
        assertEquals(quarantined, instance.isQuarantined());
    }

    /**
     * Test of getInfectTime method, of class Population.
     */
    @Test
    public void testGetInfectTime() {
        // Arrange
        System.out.println("getInfectTime");
        instance.setInfectTime();

        // Act
        long result = instance.getInfectTime();

        // Assert
        assertEquals(System.currentTimeMillis(), result);
    }

    /**
     * Test of setInfectTime method, of class Population.
     */
    @Test
    public void testSetInfectTime() {
        // Arrange
        System.out.println("setInfectTime");

        // Act
        instance.setInfectTime();

        // Assert
        assertEquals(instance.getInfectTime(), System.currentTimeMillis());
    }
    
}
