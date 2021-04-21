/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import org.junit.*;

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
        //instance = null;
    }

    /**
     * Test of initializeSimulation method, of class Dashboard.
     */
    @Test
    public void testInitializeSimulation() {
        // Arrange
        System.out.println("initializeSimulation");

        // Act & Assert
        // [No Assert.DoesNotThrow(() => MyMethod()); in JUnit4. :( ]
        try {
            instance.initializeSimulation();
        } catch (Exception exception){
            Assert.fail();
        }
    }

    /**
     * Test of getSimulation method, of class Dashboard.
     */
    @Test
    public void testGetSimulation() {
        // Arrange
        System.out.println("getSimulation");
        instance.initializeSimulation();

        // Act & Assert
        // [No Assert.DoesNotThrow(() => MyMethod()); in JUnit4. :( ]
        try {
            instance.getSimulation();
        } catch (Exception exception){
            Assert.fail();
        }
    }
}
