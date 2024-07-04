package Model;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void addStudent(int id, String name, double marks) {
        students.add(new Student(id, name, marks));
    }

    public boolean editStudent(int id, String name, double marks) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                students.add(new Student(id, name, marks));
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        return students.removeIf(student -> student.getId() == id);
    }

    public Student searchStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        System.out.println("+------------+----------------------+--------+------------+");
        System.out.println("| Student ID | Name                 | Marks  | Rank       |");
        System.out.println("+------------+----------------------+--------+------------+");
        for (Student student : students) {
            System.out.println(student.toFormattedString());
        }
        System.out.println("+------------+----------------------+--------+------------+");
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getMarks());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        boolean fileLoaded = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double marks = Double.parseDouble(parts[2]);
                    addStudent(id, name, marks);
                }
            }
            fileLoaded = true;
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileLoaded) {
            System.out.println("Students loaded from " + filename);
        }
    }


    // Bubble Sort method
    public void bubbleSort() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getMarks() < students.get(j + 1).getMarks()) {
                    // Swap students[j] and students[j+1]
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    // Quick Sort method
    public void quickSort() {
        quickSort(0, students.size() - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = students.get(high).getMarks();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (students.get(j).getMarks() <= pivot) {
                i++;
                // Swap students[i] and students[j]
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        // Swap students[i+1] and students[high] (or pivot)
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        return i + 1;
    }
}
