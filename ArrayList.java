//@author Amy Dixon

import java.util.Arrays;
import java.util.Random;

class ArrayList<T> {
    private T[] data;    // backing store
    private int count = 0;
    private int next;     // where the next free array element is

    //instantiate capacity to avoid having to allocate space each time you insert
    public ArrayList() {
        this(100);
    }

    public ArrayList(int capacity) {
        data = (T[]) new Object[capacity]; // memory allocation
        next=0;
    }

    //get specific object within array length
    public T get(int index) {
        if (index < count) {
            return data[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    //add to array
    public void add(T element) {
        if(data.length - count <= data.length/2) {
            this.resize(); //resize to ensure that array is large enough
        }

        data[count++] = element;
    }

    //delete from array
    public T remove(int index) {
        if(index < count) {
            T object = data[index];
            int temporary = index;
            data[index] = null;

            //replace current index with temp as you delete
            while (temporary < count) {
                data[temporary] = data[temporary+1];
                data[temporary+1] = null;
                temporary++;
            }

            count--; //work backwards
            return object;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    //resize array
    private void resize() {
        T[] tempElements = (T[]) new Object[data.length * 2]; // allocation
        for(int i = 0; i<data.length; i++) {
            tempElements[i] = data[i];
        }
        data = tempElements;
    }

    public T getDataAt(int idx) {
        return data[idx];
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        T[] tempElements = (T[]) new Object[next]; // allocation
        for(int i=0; i<next; i++) {
            tempElements[i] = data[i];
        }
        return Arrays.toString(tempElements);
    }

    //driver
    public static void main(String[] args) {
        Random randoNums = new Random();
        ArrayList<Integer> list = new ArrayList<>();

        //set max value to be number of elements in list
        for (int i = 0; i < 100; i++) {
            list.add(randoNums.nextInt(100));
            System.out.print(list.get(i) + " ");
        }

        //start time for testing
        final long startAdd = System.nanoTime();
        final long startDelete = System.nanoTime();

        //tests

        //add
        System.out.println();
        System.out.println("Element at position 5:" + list.get(5));
        list.add(new Integer(999));
        final long endAdd = System.nanoTime();
        System.out.println(("Adding at end took " + ((endAdd - startAdd)) + " nanoseconds"));
        System.out.println();

        //add quarter, 3-quarters through
        final long startQuart = System.nanoTime();
        System.out.println();
        System.out.println("Element at position 75:" + list.get(75));
        list.add(new Integer(999));
        final long endAddQuart = System.nanoTime();
        System.out.println(("Adding at end took " + ((endAddQuart - startQuart)) + " nanoseconds"));
        System.out.println();

        System.out.println("Total List size: " + list.size());

        //delete
        System.out.println("Removing element at position 5: " + list.remove(5));
        final long endDelete = System.nanoTime();
        System.out.println(("Removing at position 5 took " + ((endDelete - startDelete)) + " nanoseconds"));
        System.out.println();

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }

        //delete from 3/4
        System.out.println("Removing element at position 75: " + list.remove(25));
        final long endDeleteQuart = System.nanoTime();
        System.out.println(("Removing at position 75 took " + ((endDeleteQuart - startDelete)) + " nanoseconds"));
        System.out.println();

    }
    //TODO sort AL


}
