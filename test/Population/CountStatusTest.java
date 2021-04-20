package Population;

import java.awt.Rectangle;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

/**
 *
 * @author moumita
 */
@RunWith(Parameterized.class)
public class CountStatusTest {
    private Population instance;
    private Map<String,Boolean> conditions;

    boolean isQuarantined;
    boolean isFirstInfected;
    int status;
    int hospitalCapacity;
    boolean comorbidity;

    int expectedStatus;
    int expectedCountHospitalized;
    boolean expectedQuarantined;
    long infectTime;

    String testName;

    @Parameterized.Parameters
    public static Collection newVitalSign() {
        return Arrays.asList(new Object[][] {
                {false, false, 1, 10, true, 2, 1, true, System.currentTimeMillis(),
                        "infected with comorbidity - get patient hospitalized"},
                {false, false, 1, -1, true, 2, 0, false, System.currentTimeMillis(),
                        "infected with comorbidity - not yet hospitalized"},
                {false, false, 1, 0, false, 3, 0, false, System.currentTimeMillis() - 10000,
                        "infected without comorbidity - not required to get hospitalized"},

                {false, false, 2, 0, true, 4, 0, false, System.currentTimeMillis() - 10000,
                        "infected with comorbidity - hospitalized - after 3 seconds"},

                {false, false, 3, 0, true, 3, 0, false, System.currentTimeMillis() - 10000,
                        "other status - after 5 seconds"},
        });
    }

    public CountStatusTest(boolean isQuarantined,
        boolean isFirstInfected,
        int status,
        int hospitalCapacity,
        boolean comorbidity,
        int expectedStatus,
        int expectedCountHospitalized,
        boolean expectedQuarantined,
        long infectTime,
        String testName){

        this.isQuarantined = isQuarantined;
        this.isFirstInfected = isFirstInfected;
        this.status = status;
        this.hospitalCapacity = hospitalCapacity;
        this.comorbidity = comorbidity;
        this.expectedStatus = expectedStatus;
        this.expectedCountHospitalized = expectedCountHospitalized;
        this.expectedQuarantined = expectedQuarantined;
        this.infectTime = infectTime;
        this.testName = testName;
    }

    @Before
    public void setUp() {
        conditions = new HashMap<>();
        conditions.put("compareVirus", true);
        conditions.put("comorbidity", true);
        conditions.put("prone", true);
        conditions.put("distancingCheck", true);
        conditions.put("infected", true);
        conditions.put("quarantine", true);
        conditions.put("vaccinated", true);
//        instance = new Population(100, 100, conditions, 500, 
//                groupEvent:false, rectangle: null, populatioNum: 1000, compareVirus: false, rNaught: 0);
                
        instance = new Population(100, 100, conditions, 500, 
                false, null, 1000, false, 0);
    }

    @After
    public void tearDown() {
        instance = null;
        conditions = null;
    }

    /**
     * Test of countStatus method, of class Population.
     */
    @Test
    public void testCountStatus() {
        // Arrange
        System.out.println(testName);

        instance.setQuarantined(isQuarantined);
        instance.setFirstInfected(isFirstInfected);
        instance.setStatus(status);
        instance.setHospitalCapacity(hospitalCapacity);
        instance.setInfectTime(infectTime);
        instance.setComorbidity(comorbidity);

        // Act
        instance.countStatus();

        // Assert
        assertEquals(expectedQuarantined, instance.isQuarantined());
        assertEquals(expectedStatus, instance.getStatus());
        assertEquals(expectedCountHospitalized, instance.getCountHospitalized());
    }
}
