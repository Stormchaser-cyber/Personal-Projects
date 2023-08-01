import java.util.Comparator;
import java.util.function.Function;
import java.util.Random;

/** Used as part of a comparative study of different sorting algorithms. */
public class Quicksort <T extends Comparable<T>> {

    /** start time in nanoseconds */
    private long startNS;
    /** end time in nanoseconds */
    private long endNS;

    /** number of operations in sorting process.
     machine-independent measure of runtime. */
    private long operations;

    /** Comparator to determine ordering */
    Comparator<T> orderBy = null;

    /** max value within array - determines histogram size */
    int max = 1;

    /** Constructor. Initializes metrics to 0. */
    public Quicksort() {
        startNS = 0;
        endNS = 0;
        operations = 0;
    }

    /** Method call from outside class to sort input array.
     You must put elements into the array that is passed in sorted order.
     @param inArray array that will contain sorted elements upon fn return.
     @param m maximum value used to size the histogram (i.e. C array)
     @param comparator used for ordering elements
     */
    public void sort(T[] inArray, int m, Comparator<T> comparator ) {
        // initialize everything
        endNS = 0;
        operations = 0;
        orderBy = comparator;
        max = m;

        // IMPORTANT:
        // operations = total number of comparisons

        // start the timer
        startNS = System.nanoTime();

        // sort the array recording the time
        sortTimed(inArray, 0, inArray.length-1);

        // mark the finish time
        endNS = System.nanoTime();

        // this for debugging purposes to check timer.
        //System.out.println("start="+startNS+". end="+endNS+". elapsed="+(endNS-startNS)/1000);
    }

    /** Getter for number of operations. */
    public long operations() {
        return operations;
    }

    /** Getter for elapsed time in microsecs based on stored start and end time, which are in ns */
    public long elapsedTimeUS() {
        return (endNS - startNS) / 1000;
    }

    /** Randomized Quick Sort algorithm.
     @param array original array where the sorted elements need to go
     @param p starting index of subarray
     @param r ending index of subarray
     */
    void sortTimed(T[] array, int p, int r) {
        if (p < r) {
            int q = partition(array, p, r);
            sortTimed(array, p, q-1);
            sortTimed(array, q+1, r);
            operations += 3;
        }


    }

    int partition(T[] array, int p, int r) {
        random(array, p, r);
        T pivot = array[r];

        int i = (p - 1);
        for (int j = p; j < r; j++) {
            operations++;
            if(array[j].compareTo(pivot) < 0) {
                operations+= 4;

                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        T temp = array[i+1];
        array[i + 1] = array[r];
        array[r] = temp;
        operations += 3;

        return i + 1;
    }

    void random(T[] array, int low, int high) {
        Random random = new Random();
        int pivot = random.nextInt(high - low) + low;

        T temp = array[pivot];
        array[pivot] = array[high];
        array[high] = temp;
    }
}