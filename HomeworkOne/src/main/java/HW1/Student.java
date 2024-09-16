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
    private ArrayList<Integer> grades;

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

    public void setGrades(ArrayList<Integer> grades) {
        this.grades = grades;
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
    }

    /**
     * Computes the average grade for the student.
     * 
     * @return The computed grade average
     */
    public double getAverageGrade() {
        int sum = 0;
        int size = this.getGrades().size();
        for (int i = 0; i < size; i++) {
            sum += this.getGrades().get(i);
        }
        return (size == 0) ? 0 : (sum / size);
    }
}
