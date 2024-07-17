//program to callow teacher to enter students grades and compute their average,highestand lowest scores using 
//arraylist

import java.util.ArrayList;
import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();

        
        System.out.println("Enter student grades. Type -1 to finish:");
        while (true) {
            int grade = scanner.nextInt();
            if (grade == -1) {
                break;
            }
            grades.add(grade);
        }

        if (grades.size() == 0) {
            System.out.println("No grades were entered.");
        } else {
            int highest = getHighestGrade(grades);
            int lowest = getLowestGrade(grades);
            double average = getAverageGrade(grades);

            System.out.println("Highest grade: " + highest);
            System.out.println("Lowest grade: " + lowest);
            System.out.println("Average grade: " + average);
        }
    }

    private static int getHighestGrade(ArrayList<Integer> grades) {
        int highest = grades.get(0);
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    private static int getLowestGrade(ArrayList<Integer> grades) {
        int lowest = grades.get(0);
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    private static double getAverageGrade(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }
}