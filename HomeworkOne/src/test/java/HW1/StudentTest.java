package HW1;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * A test class to test the efficacy of the student class' methods
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class StudentTest {
    @Test
    // Test sample object creation
    public void studentObjectTest() {
        Student testStudent = new Student("John","15905047");
        assertEquals("John", testStudent.getName());
        assertEquals("15905047", testStudent.getId());
    }

    @Test
    // Test grade manipulation
    public void studentGradeTest() {
        Student testStudent = new Student("John","15905047");
        testStudent.addGrade(1);
        ArrayList<Integer> sampleGrades = new ArrayList<Integer>();
        sampleGrades.add(1);
        assertEquals(sampleGrades, testStudent.getGrades());

        // Add a ton of grades and calculate average
        int sum = 0;
        int count = 1;
        for (int i = 2; i < 10000000; i *= 1.5) {
            sum += i;
            count++;
            testStudent.addGrade(i);
        }
        assertEquals(sum / count, testStudent.getAverageGrade(), 0);
    }
}
