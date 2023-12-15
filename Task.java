/*
 * Written by Nick Lauer
 */
public class Task {
    private String action;
    private int priority;

    // Constructor to create a new Task with description and priority
    public Task(String action, int priority) {
        // Check that the action is not null or empty; set to "none" if it is
        if (action == null || action.trim().isEmpty()) {
            this.action = "none";
        } else {
            this.action = action;
        }

        // Check that the priority is within the valid range (0 to 4); set to 4 if out of range
        if (priority < 0 || priority > 4) {
            this.priority = 4;
        } else {
            this.priority = priority;
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        if (action != null && !action.trim().isEmpty()) {
            this.action = action;
        }
    }

    // Getter method to retrieve the priority of the Task
    public int getPriority() {
        return priority;
    }

    // Setter method to update the priority of the Task
    public void setPriority(int priority) {
        if (priority >= 0 && priority <= 4) {
            this.priority = priority;
        }
    }

    // Custom equals method to compare Task objects
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return priority == task.priority && action.equals(task.action);
    }

    // Custom hashCode method to generate a hash code for Task objects
    public int hashCode() {
        return 31 * action.hashCode() + priority;
    }

    // toString method to provide a string of a Task object
    public String toString() {
        return "[Task] Priority: " + priority + " Task: " + action;
    }
}