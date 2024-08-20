import Model.Student;
import Model.StudentManager;

import java.util.Scanner;

public class Main {
    private static final String TITLE =
            "   _____ _             _            _     __  __                                                   _   \n" +
                    "  / ____| |           | |          | |   |  \\/  |                                                 | |  \n" +
                    " | (___ | |_ _   _  __| | ___ _ __ | |_  | \\  / | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_ \n" +
                    "  \\___ \\| __| | | |/ _` |/ _ \\ '_ \\| __| | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|\n" +
                    "  ____) | |_| |_| | (_| |  __/ | | | |_  | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_ \n" +
                    " |_____/ \\__|\\__,_|\\__,_|\\___|_| |_|\\__| |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__|\n" +
                    "                                                                    __/ |                              \n" +
                    "                                                                   |___/                               ";

    public static void main(String[] args) {
        System.out.println(TITLE);
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student by ID");
            System.out.println("5. Sort Students by Marks");
            System.out.println("6. Display Students");
            System.out.println("7. Save to File");
            System.out.println("8. Load from File");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student marks: ");
                    double marks = scanner.nextDouble();
                    manager.addStudent(id, name, marks);
                    System.out.println("Student added.");
                    break;

                case 2:
                    System.out.print("Enter student ID to edit: ");
                    id = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new marks: ");
                    marks = scanner.nextDouble();
                    if (manager.editStudent(id, name, marks)) {
                        System.out.println("Student details updated.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter student ID to delete: ");
                    id = scanner.nextInt();
                    if (manager.deleteStudent(id)) {
                        System.out.println("Student deleted.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.println("Search by:");
                    System.out.println("1. Hash Method");
                    System.out.println("2. Linear Search");
                    System.out.print("Choose a search method: ");
                    int searchChoice = scanner.nextInt();
                    System.out.print("Enter student ID to search: ");
                    int searchId = scanner.nextInt();
                    Student student = null;

                    if (searchChoice == 1) {
                        student = manager.hashSearchByID(searchId);
                    } else if (searchChoice == 2) {
                        student = manager.linearSearchByID(searchId);
                    } else {
                        System.out.println("Invalid search method choice!");
                    }

                    if (student != null) {
                        System.out.println("Student found: " + student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.println("1. Tim Sort");
                    System.out.println("2. Bubble Sort");
                    System.out.println("3. Quick Sort");
                    System.out.println("4. Merge Sort");
                    System.out.println("5. Heap Sort");
                    System.out.print("Choose sorting method: ");
                    int sortChoice = scanner.nextInt();
                    if (sortChoice == 1) {
                        manager.timSort();
                        System.out.println("Students sorted by Tim Sort.");
                    } else if (sortChoice == 2) {
                        manager.bubbleSort();
                        System.out.println("Students sorted by Bubble Sort.");
                    } else if (sortChoice == 3) {
                        manager.quickSort();
                        System.out.println("Students sorted by Quick Sort.");
                    } else if (sortChoice == 4) {
                        manager.mergeSort();
                        System.out.println("Students sorted by Merge Sort.");
                    } else if (sortChoice == 5) {
                        manager.heapSort();
                        System.out.println("Students sorted by Heap Sort.");
                    } else {
                        System.out.println("Invalid choice! Try again.");
                    }
                    break;

                case 6:
                    manager.displayStudents();
                    break;

                case 7:
                    System.out.print("Enter filename: ");
                    String saveFilename = scanner.next();
                    manager.saveToFile(saveFilename);
                    System.out.println("Students saved to " + saveFilename);
                    break;

                case 8:
                    System.out.print("Enter filename: ");
                    String loadFilename = scanner.next();
                    manager.loadFromFile(loadFilename);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
            System.out.println();
        }
    }
}
