package HW1;

import java.util.ArrayList;

/**
 * A student account for a school system.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class Student {
    // Needed attributes
    private String name;
    private String studentId;
    private ArrayList<Integer> grades = new ArrayList<Integer>();

    // Privately keep track of grade info
    private int numGrades = 0;
    private int sum = 0;

    // Boilerplate
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String studentId) {
        this.studentId = studentId;
    } 

    public String getId() {
        return this.studentId;
    }

    public ArrayList<Integer> getGrades() {
        return this.grades;
    }

    /**
     * Creates a new student with a name and student ID.
     * 
     * @param name The student's name
     * @param studentId the student's Id
     */
    public Student(String name, String studentId) {
        this.setName(name);
        this.setId(studentId);
    }

    /**
     * Adds a grade to the student's list of grades.
     * 
     * @param grade The grade to be added
     */
    public void addGrade(int grade) {
        this.getGrades().add(new Integer(grade));

        // Increase the counter and sum for constant average access
        numGrades++;
        sum += grade;
    }

    /**
     * Computes the average grade for the student.
     * !Time complexity reduces to O(1), running totals kept
     * 
     * @return The computed grade average
     */
    public double getAverageGrade() {
        return (numGrades == 0) ? 0 : (sum / numGrades);
    }
}
