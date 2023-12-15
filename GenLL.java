/*
 * Written by Nick Lauer
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenLL<T> implements Iterable<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head; 

    // Constructor to initialize an empty linked list
    public GenLL() {
        head = null;
    }

    // Method to add a new element to the linked list
    public void add(T newData) {
        // Check for duplicates by calling the contains() method
        if (contains(newData)) {
            System.out.println("Duplicate task. Not added.");
            return;
        }

        // Create a new node with the new data and insert it at the beginning
        Node<T> newNode = new Node<>(newData);
        newNode.next = head;
        head = newNode;
    }

    // Method to check if a specific element is present in the linked list
    public boolean contains(T data) {
        for (Node<T> temp = head; temp != null; temp = temp.next) {
            if (temp.data.equals(data)) {
                return true; 
            }
        }
        return false; 
    }

    // Method to remove a specific element from the linked list
    public boolean remove(T dataToRemove) {
        Node<T> temp = head;
        Node<T> prev = null;

        // Iterate through the linked list to find the element to remove
        while (temp != null && !temp.data.equals(dataToRemove)) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            return false; 
        } else if (prev == null) {
            head = temp.next; 
        } else {
            prev.next = temp.next;
        }

        return true; 
    }

    // Method to print the elements in the linked list
    public void print() {
        // Iterate through the linked list and print each element
        for (Node<T> temp = head; temp != null; temp = temp.next) {
            System.out.println(temp.data);
        }
    }

    // Iterator implementation to enable iterating over elements in the linked list
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            // Check if there are more elements to iterate over
            public boolean hasNext() {
                return current != null;
            }

            // Get the next element in the iteration
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}