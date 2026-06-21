package TaskManagementSystem;

class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class SinglyLinkedList {
    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    // Add task
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Added task: " + task.getTaskName());
    }

    // Search task by ID
    public Task searchTask(String taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId().equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null; // Not found
    }

    // Traverse list
    public void traverseTasks() {
        Node current = head;
        System.out.println("Task List:");
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Delete task by ID
    public void deleteTask(String taskId) {
        if (head == null) return;

        // If head needs to be removed
        if (head.task.getTaskId().equals(taskId)) {
            head = head.next;
            System.out.println("Deleted task with ID: " + taskId);
            return;
        }

        Node current = head;
        while (current.next != null && !current.next.task.getTaskId().equals(taskId)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println("Deleted task with ID: " + taskId);
        } else {
            System.out.println("Task with ID " + taskId + " not found.");
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        
        list.addTask(new Task("T1", "Design Database", "In Progress"));
        list.addTask(new Task("T2", "Develop API", "Not Started"));
        
        list.traverseTasks();
        
        System.out.println("\nSearching for T2:");
        System.out.println(list.searchTask("T2"));
        
        System.out.println("\nDeleting T1:");
        list.deleteTask("T1");
        
        list.traverseTasks();
    }
}
