/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author moumita
 */
public class DashboardTest {
    Dashboard instance;

    public DashboardTest() {
    }
    
    @Before
    public void setUp() {
        instance = new Dashboard();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of initializeSimulation method, of class Dashboard.
     */
    @Test
    public void testInitializeSimulation() {
        System.out.println("initializeSimulation");

        instance.initializeSimulation();
    }

    /**
     * Test of getSimulation method, of class Dashboard.
     */
    @Test
    public void testGetSimulation() {
        System.out.println("getSimulation");
        Dashboard.main(null);
        instance.initializeSimulation();

        instance.getSimulation();
    }

    /**
     * Test of main method, of class Dashboard.
     * The main function shouldn't throw any exception.
     */
    @Test
    public void testMain() {
        // Arrange
        System.out.println("main");
        String[] args = null;

        // Act & Assert
        // [No Assert.DoesNotThrow(() => MyMethod()); in JUnit4. :( ]
        try {
            Dashboard.main(args);
        } catch (Exception exception){
            Assert.fail();
        }
    }
    
}
