//@author Amy Dixon
//some code adapted from: https://www.geeksforgeeks.org/linked-list-set-3-deleting-node/
//middle insert code adapted from: https://www.geeksforgeeks.org/insert-node-middle-linked-list/

import java.util.Random;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int count;

    private class Node<T> {
        private T data;
        private Node<T> next;

        //empty constructor
        //set pointers to null until iteration begins
        private Node() {
            this.data = null;
            this.next = null;
        }

        public Node(T object) {
            this.data = object;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    //constructor for LinkedList
    public LinkedList() {
        Node<T> node = new Node<>(); //instantiate
        this.head = node;
        this.tail = this.head; //set node's pointer with looping reference to next and previous list elements
    }

    //add to end of list
    public void add(T data) {
        Node<T> stuffToAdd = new Node<>(data); //instantiate

        //if list is empty, add stuff to first node location and reset pointer
        if(this.head.getData() == null) {
            this.head = stuffToAdd;
            this.tail = this.head; //reset looping reference
        } else {
            //add data to tail of list
            this.tail.setNext(stuffToAdd);
            this.tail = stuffToAdd;
        }
        count++;
    }

    //add to middle of list
    public void addToMiddle(T data) {

        //if list is empty
        if(head == null) {
            head = new Node<>(data);
        } else {
            //get new node
            Node newNode = new Node(data);
            Node pointer = head;
            int length = 0;

            while(pointer!=null) {
                length++;
                pointer = pointer.next;
            }

            // 'count' the number of nodes after which
            // the new node is to be inserted
            int count = ((length % 2) == 0) ? (length / 2) :
                    (length + 1) / 2;
            pointer = head;

            // point to the node after which the new node is to be inserted
            while (count-- > 1) {
                pointer = pointer.next;
            }

            // insert the 'newNode' and adjust
            // the required links
            newNode.next = pointer.next;
            pointer.next = newNode;
        }
    }

    //remove from list
    public void delete(int data) {
        //throw error if list is null
        if(head == null) throw new RuntimeException("Unable to delete!");

        //store new head node
        Node temporary = head;

        //check if list if empty
        if(head == null) {
            return;
        }

        //if head needs to be deleted
        if(data == 0) {
            head = temporary.next; //switch head
            return;
        }

        //find node before the one we need to delete
        for (int i = 0; temporary!= null && i<data-1; i++) {
            temporary = temporary.next;
        }

        //check if node to be deleted is more than number of nodes in current list
        if (temporary == null || temporary.next == null) {
            return;
        }

        //store pointer to next node to be deleted
        Node next = temporary.next.next;

        //unlink deleted
        temporary.next = next;

    }

    //traverse and print list
    public void printLL() {
            Node node = head;
            while(node!=null) {
                System.out.print(node.data + " ");
                node = node.next;
        }
    }

    //get list size
    public int size() {
        return count;
    }

    //driver
    public static void main(String[] args) {
//        Random randoNums = new Random();
        LinkedList<Integer> list = new LinkedList<>();

        //generate list of desired size (can be randomly-generated or in order)
        for(int i=0; i<100; i++) {
//            list.add(randoNums.nextInt(100));
            list.add(i);
        }

        //head time for testing
        final long startAdd = System.nanoTime();

        //add static elements to tail of list for testing
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        int x = 30;
        list.addToMiddle(x);

        final long endAdd = System.nanoTime();

        list.printLL();
        System.out.println();
        System.out.println(("Adding: " + ((endAdd - startAdd)) + " nanoseconds"));
        System.out.println();

        final long startDelete = System.nanoTime();

        //delete from anywhere in list
        list.delete(35);

        final long endDelete = System.nanoTime();

        System.out.println("List after deletion: ");
        list.printLL();
        System.out.println();
        System.out.println(("Deleting: " + ((endDelete - startDelete)) + " nanoseconds"));
        System.out.println();

        //TODO sort LL

    }
}
