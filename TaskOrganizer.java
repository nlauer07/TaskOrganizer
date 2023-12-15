/*
 * Written by Nick Lauer
 */
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaskOrganizer {
    private GenLL<Task>[] organizedTasks;

    public TaskOrganizer() {
        // Initialize an array of 5 linked lists to organize tasks by priority
        organizedTasks = new GenLL[5];
        for (int i = 0; i < organizedTasks.length; i++) {
            // Create a linked list for each priority level
            organizedTasks[i] = new GenLL<>();
        }
    }

    public void addTask(String action, int priority) {
        // Create a new task and add it to the appropriate priority-linked list
        Task newTask = new Task(action, priority);
        organizedTasks[priority].add(newTask);
    }

    public void removeTask(String action, int priority) {
        // Remove a task with the action and priority from the corresponding linked list
        Task taskToRemove = new Task(action, priority);
        if (!organizedTasks[priority].remove(taskToRemove)) {
            // Display a message if the task is not found in the list
            System.out.println("Task not found. No task removed.");
        } else {
            System.out.println("Task removed successfully.");
        }
    }

    public void displayTasks() {
        System.out.println("Displaying all tasks:");
        for (int i = 0; i < organizedTasks.length; i++) {
            // Display tasks organized by priority
            System.out.println("Priority " + i + ":");
            organizedTasks[i].print();
        }
    }

    public void readTasksFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the tasks file: ");
        String filename = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length != 2) {
                    continue;
                }

                try {
                    int priority = Integer.parseInt(parts[0]);
                    String action = parts[1];

                    if (priority >= 0 && priority <= 4 && action != null && !action.trim().isEmpty()) {
                        // Add tasks from the file to the priority-linked lists
                        addTask(action, priority);
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid number format
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks file: " + e.getMessage());
        }
    }

    public void writeTasksToFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename to save tasks: ");
        String filename = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < organizedTasks.length; i++) {
                GenLL<Task> taskList = organizedTasks[i];
                for (Task task : taskList) {
                    // Write tasks from the priority-linked lists to a file
                    writer.write(task.getPriority() + "\t" + task.getAction() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void userInterface() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------Task Manager---------");

        while (true) {
            System.out.println("1. Add a task");
            System.out.println("2. Remove a task");
            System.out.println("3. Display all tasks");
            System.out.println("4. Read tasks from file");
            System.out.println("5. Write tasks to file");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    System.out.print("Enter task action: ");
                    String action = scanner.nextLine();
                    int priority = -1; // Initializing with an invalid priority
                    
                    while (priority < 0 || priority > 4) {
                        System.out.print("Enter task priority (0-4): ");
                        try {
                            priority = scanner.nextInt();
                            if (priority < 0 || priority > 4) {
                                System.out.println("Invalid priority. Please enter a number between 0 and 4.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer.");
                            scanner.next();  
                        }
                    }
                    addTask(action, priority);
                    break;
                case 2:
                    System.out.print("Enter task action to remove: ");
                    action = scanner.nextLine();
                    priority = -1; 
                    
                    while (priority < 0 || priority > 4) {
                        System.out.print("Enter task priority to remove (0-4): ");
                        try {
                            priority = scanner.nextInt();
                            if (priority < 0 || priority > 4) {
                                System.out.println("Invalid priority. Please enter a number between 0 and 4.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer.");
                            scanner.next();  
                        }
                    }
                    removeTask(action, priority);
                    break;
                case 3:
                    displayTasks();
                    break;
                case 4:
                    readTasksFromFile();
                    break;
                case 5:
                    writeTasksToFile();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    public static void main(String[] args) {
        TaskOrganizer organizer = new TaskOrganizer();
        organizer.userInterface();
    }
}