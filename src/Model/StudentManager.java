package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class StudentManager {
    private List<Student> students;
    private Map<Integer, Student> studentMap;

    public StudentManager() {
        students = new ArrayList<>();
        studentMap = new HashMap<>();
    }

    public void addStudent(int id, String name, double marks) {
        Student student = new Student(id, name, marks);
        students.add(student);
        studentMap.put(id, student);
    }

    public boolean editStudent(int id, String name, double marks) {
        if (studentMap.containsKey(id)) {
            Student student = new Student(id, name, marks);
            students.removeIf(s -> s.getId() == id);
            students.add(student);
            studentMap.put(id, student);
            return true;
        }
        return false;
    }
    public boolean deleteStudent(int id) {
        if (studentMap.containsKey(id)) {
            students.removeIf(s -> s.getId() == id);
            studentMap.remove(id);
            return true;
        }
        return false;
    }

    // Search Student by ID method using Hash search
    public Student hashSearchByID(int id) {
        return studentMap.get(id);
    }

    // Search Student by ID method using binary search
    public Student binarySearchByID(int id) {
        students.sort(Comparator.comparingInt(Student::getId)); // Ensure the list is sorted by ID

        int left = 0;
        int right = students.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midId = students.get(mid).getId();

            if (midId == id) {
                return students.get(mid); // Student found
            } else if (midId < id) {
                left = mid + 1; // Search the right half
            } else {
                right = mid - 1; // Search the left half
            }
        }

        return null; // Student not found
    }

    // Search Student by ID method using linear search
    public Student linearSearchByID(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student; // Student found
            }
        }
        return null; // Student not found
    }
    

    public void displayStudents() {
        System.out.println("+------------+------------------------+--------+------------+");
        System.out.println("| Student ID | Name                   | Marks  | Rank       |");
        System.out.println("+------------+------------------------+--------+------------+");
        for (Student student : students) {
            System.out.println(student.toFormattedString());
        }
        System.out.println("+------------+------------------------+--------+------------+");
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

    // Tim Sort
    public void timSort() {
    students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
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

    // Merge Sort
    public void mergeSort() {
        mergeSort(0, students.size() - 1);
    }
    
    private void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }
    
    private void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
    
        List<Student> leftArray = new ArrayList<>(n1);
        List<Student> rightArray = new ArrayList<>(n2);
    
        for (int i = 0; i < n1; i++)
            leftArray.add(students.get(left + i));
        for (int j = 0; j < n2; j++)
            rightArray.add(students.get(mid + 1 + j));
    
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray.get(i).getMarks() >= rightArray.get(j).getMarks()) {
                students.set(k, leftArray.get(i));
                i++;
            } else {
                students.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }
    
        while (i < n1) {
            students.set(k, leftArray.get(i));
            i++;
            k++;
        }
    
        while (j < n2) {
            students.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }
    
    // Heap Sort
    public void heapSort() {
        int n = students.size();
    
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i);
    
        // Extract elements from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            Student temp = students.get(0);
            students.set(0, students.get(i));
            students.set(i, temp);
    
            // Call max heapify on the reduced heap
            heapify(i, 0);
        }
    }
    
    private void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
    
        if (left < n && students.get(left).getMarks() > students.get(largest).getMarks())
            largest = left;
    
        if (right < n && students.get(right).getMarks() > students.get(largest).getMarks())
            largest = right;
    
        if (largest != i) {
            Student swap = students.get(i);
            students.set(i, students.get(largest));
            students.set(largest, swap);
    
            heapify(n, largest);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Student student : students) {
            builder.append(student.toString()).append("\n");
        }
        return builder.toString();
    }
}
