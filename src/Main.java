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
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Sort Students by Marks");
            System.out.println("6. Display Students");
            System.out.println("7. Save to File");
            System.out.println("8. Load from File");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    manager.addStudent(id, name, marks);
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new Name: ");
                    String editName = scanner.nextLine();
                    System.out.print("Enter new Marks: ");
                    double editMarks = scanner.nextDouble();
                    boolean editSuccess = manager.editStudent(editId, editName, editMarks);
                    if (editSuccess) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student ID not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter ID: ");
                    int deleteId = scanner.nextInt();
                    boolean deleteSuccess = manager.deleteStudent(deleteId);
                    if (deleteSuccess) {
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student ID not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter ID: ");
                    int searchId = scanner.nextInt();
                    Student student = manager.searchStudent(searchId);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 5:
                    System.out.println("1. Bubble Sort");
                    System.out.println("2. Quick Sort");
                    System.out.print("Choose sorting method: ");
                    int sortChoice = scanner.nextInt();
                    if (sortChoice == 1) {
                        manager.bubbleSort();
                        System.out.println("Students sorted by Bubble Sort.");
                    } else if (sortChoice == 2) {
                        manager.quickSort();
                        System.out.println("Students sorted by Quick Sort.");
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
                default:
                    System.out.println("Invalid choice! Try again.");
            }
            System.out.println();
        }
    }
}
