import java.util.Arrays;
import java.util.Random;

/**
 * @author tedst
 */
public class Main {

    private static Random generator = new Random();

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //driver code to test implementation
        int array[] = {8,7,6,5,4,3,2,1};

        System.out.println("Given array:");
        printArray(array);

        MergeSort merge = new MergeSort();
        merge.sort(array, array.length);

        System.out.println("\nSorted array");
        printArray(array);

        //testing an array of 5 numbers within a range of 5
        runTest(1, merge, 5, 5); // this was to make sure quicksort was working first
  //Testing array of size 10 -------------------------------------------------------------------------------------------
        //testing an array of 10 numbers within a range of 10
        runTest(2, merge,10 ,10);

        //testing an array of 10 numbers within a range of 25
        runTest(3, merge,10, 25);

        //testing an array of 10 numbers within a range of 50
        runTest(4, merge,10, 50);

        //testing an array of 10 numbers within a range of 100
        runTest(5, merge,10, 100);

  //Testing array of size 100 ------------------------------------------------------------------------------------------
        //testing an array of 100 numbers within a range of 100
        runTest(6, merge, 100, 100);

        //testing an array of 100 numbers within a range of 250
        runTest(7, merge, 100, 250);

        //testing an array of 100 numbers within a range of 500
        runTest(8, merge, 100, 500);

        //testing an array of 100 numbers within a range of 1,000
        runTest(9, merge, 100, 1000);

  //Testing array of size 1,000 ----------------------------------------------------------------------------------------
        //testing an array of 1,000 numbers within a range of 1,000
        runTest(10, merge, 1000, 1000);

        //testing an array of 1,000 numbers within a range of 2,500
        runTest(11, merge,1000, 2500);

        //testing an array of 1,000 numbers within a range of 5,000
        runTest(12, merge,1000, 5000);

        //testing an array of 1,000 numbers within a range of 10,000
        runTest(13, merge,1000, 10000);

  //Testing array of size 10,000 ---------------------------------------------------------------------------------------
        //testing an array of 10,000 numbers within a range of 10,000
        runTest(14, merge,10000, 10000);

        //testing an array of 10,000 numbers within a range of 25,000
        runTest(15, merge,10000, 25000);

        //testing an array of 10,000 numbers within a range of 50,000
        runTest(16, merge,10000, 50000);

        //testing an array of 10,000 numbers within a range of 100,000
        runTest(17, merge,10000, 100000);

  //Testing array of size 100,000 --------------------------------------------------------------------------------------
        //testing an array of 100,000 numbers within a range of 100,000
        runTest(18, merge,100000, 100000);

        //testing an array of 100,000 numbers within a range of 250,000
        runTest(19, merge,100000, 250000);

        //testing an array of 100,000 numbers within a range of 500,000
        runTest(20, merge,100000, 500000);

        //testing an array of 100,000 numbers within a range of 1,000,000
        runTest(21, merge,100000, 1000000);

  //End Testing --------------------------------------------------------------------------------------------------------
    }

    /**
     * Runs one test on a randomly generated array with a specified size
     * @param testNumber the test number
     * @param mergeSortObject
     * @param number
     * @param range
     */
    public static void runTest(int testNumber, MergeSort mergeSortObject, int number, int range) {
        // ascetic design
        beginningTestPrintStatement(testNumber);

        // One experiment run looks like this:
        System.out.println("Using an array of " +  number + " numbers between the range of -" + range + " and " + range);
        int[] someArray = createArray( number, range); // create another array
        MergeSort.counter = 0;  // initialize the count of operations
        mergeSortObject.sort(someArray, number); // sort the array
        //System.out.println( Arrays.toString(someArray)); // print out to make sure it's sorted -- commented this part out when testing MASSIVEEEE arrays due to the extreme amount of printed information
        System.out.println("Count was " + MergeSort.counter + " operations"); // print count of operations

        //ascetic design!
        endingTestPrintStatement(testNumber);
    }

    /**
     * Creates an array of size n with values in the given range.
     * @param number The number of values to create.
     * @param range The range of the randomly created values.
     * @return An array of size number with random values in -range to +range
     */
    public static int[] createArray(int number, int range) {
        int[] answer = new int[number];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = generator.nextInt(range * 2 + 1) - range;
        }
        return answer;
    }

    /**
     * Prints out the inputted array
     * @param array the array to print
     */
    public static void printArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Ascetic for showing the beginning of tests
     * @param testNumber the test number which we are on
     */
    public static void beginningTestPrintStatement(int testNumber) {
        // If statement here is just for ascetic purposes for the lines to correctly line up after entering double digits in the test number field
        if(testNumber > 9) {
            System.out.println("Running Test #" + testNumber + " ------------------------------------------------------------");
        }
        else {
            System.out.println("Running Test #" + testNumber + " -------------------------------------------------------------");
        }
    }

    /**
     * Ascetic for showing the ending of tests
     * @param testNumber the test number which we are on
     */
    public static void endingTestPrintStatement(int testNumber) {
        // If statement here is just for ascetic purposes for the lines to correctly line up after entering double digits in the test number field
        if(testNumber > 9) {
            System.out.println("Ending Test -----------------------------------------------------------------\n");
        }
        else {
            System.out.println("Ending Test -----------------------------------------------------------------\n");
        }
    }
}
